/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.service.impl;

import com.app.ws.exceptions.CouldNotCreateRecordException;
import com.app.ws.exceptions.NoRecordFoundException;
import com.app.ws.io.dao.DAO;
import com.app.ws.io.dao.impl.MySQLDAO;
import com.app.ws.service.UsersService;
import com.app.ws.shared.dto.UserDTO;
import com.app.ws.ui.model.response.ErrorMessages;
import com.app.ws.utils.UserProfileUtils;

/**
 *
 * @author philippgottschalk
 */
public class UserServiceImpl implements UsersService{
    
    DAO database;
    
    public UserServiceImpl(){
    this.database = new MySQLDAO();
    }
    
    UserProfileUtils userProfileUtils = new UserProfileUtils();
    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = null;
        
        //Validate the required fields
            //Validierung wird clientseitig durchgeführt.
        
        //check if user exists
        UserDTO exisitingUser = this.getUserByPlayerID(user.getPlayerID());
        if(exisitingUser != null){
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALLREADY_EXIST.name());
        }
        
        // Generate secure public user id
        String userId = userProfileUtils.generateUserId(30);
        user.setUserId(userId);
        
        //Generate salt
        String salt = userProfileUtils.getSalt(30);
        
        //Generate secure password
        String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setEncryptedPassword(encryptedPassword);
        
        //record Date into a database
        returnValue = this.saveUser(user);
        
        //Return back the user profile
        return returnValue;
    }
    
    @Override
    public UserDTO getUserByPlayerID(String playerId){
        UserDTO userDto = null;

        if (playerId == null || playerId.isEmpty()) {
            return userDto;
        }

        // Connect to database 
        try {
            this.database.openConnection();
            userDto = this.database.getUserByPlayerID(playerId);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }
    
    private UserDTO saveUser(UserDTO user){
        UserDTO returnValue = null;
         // Connect to database 
        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    
    }

    public UserDTO getUser(String id) {
        UserDTO returnValue = null;
        try{
            this.database.openConnection();
            returnValue = this.database.getUser(id);
        } catch(Exception ex){
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()); 
        }finally{
            this.database.closeConnection();
        }
        return returnValue;
             
    }
    
}
