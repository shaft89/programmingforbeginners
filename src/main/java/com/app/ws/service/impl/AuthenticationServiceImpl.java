/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.service.impl;

import com.app.ws.exceptions.AuthenticationException;
import com.app.ws.io.dao.DAO;
import com.app.ws.io.dao.impl.MySQLDAO;
import com.app.ws.service.AuthenticationService;
import com.app.ws.service.UsersService;
import com.app.ws.shared.dto.UserDTO;
import com.app.ws.ui.model.response.ErrorMessages;
import com.app.ws.utils.UserProfileUtils;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philippgottschalk
 */
public class AuthenticationServiceImpl implements AuthenticationService{

    DAO database;

    public UserDTO authenticate(String playerId, String password) throws AuthenticationException {
        UsersService usersService = new UserServiceImpl();
        UserDTO storedUser = usersService.getUserByPlayerID(playerId); // User name must be unique in our system

        if (storedUser == null) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }
        

        String encryptedPassword = null;

        encryptedPassword = new UserProfileUtils().
                generateSecurePassword(password, storedUser.getSalt());

        boolean authenticated = false;
        if (encryptedPassword != null && encryptedPassword.equalsIgnoreCase(storedUser.getEncryptedPassword())) {
            if (playerId != null && playerId.equalsIgnoreCase(storedUser.getPlayerID())) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        return storedUser;
    }

    public String issueAcessToken(UserDTO userProfile) throws AuthenticationException {
        String returnValue = null; 
        
        String newSaltAsPostfix = userProfile.getSalt();
        String accessTokenMaterial = userProfile.getUserId() + newSaltAsPostfix;

        byte[] encryptedAccessToken = null;
        try {
            encryptedAccessToken = new UserProfileUtils().encrypt(userProfile.getEncryptedPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Faled to issue secure access token");
        }

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        // Split token into equal parts
        int tokenLength = encryptedAccessTokenBase64Encoded.length();

        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);

        userProfile.setToken(tokenToSaveToDatabase);
        updateUserProfile(userProfile);
        return returnValue;
    }
    
    private void updateUserProfile(UserDTO userProfile) {
        this.database = new MySQLDAO();
        try {
            database.openConnection();
            database.updateUser(userProfile);
        } finally {
            database.closeConnection();
        }
    }

    public void resetSecurityCridentials(String password, UserDTO userProfile) {
        //Generate new salt
        
        UserProfileUtils userUtils = new UserProfileUtils();
        String salt = userUtils.getSalt(30);
        
        //Generate new password
        String securePassword = userUtils.generateSecurePassword(password, salt);
        userProfile.setSalt(salt);
        userProfile.setEncryptedPassword(securePassword);
        
        //update user 
        
        updateUserProfile(userProfile);

        
    }
    
}
