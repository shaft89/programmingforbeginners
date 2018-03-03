/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.shared.dto;

import com.app.ws.service.impl.AuthenticationServiceImpl;
import com.app.ws.ui.model.request.LoginCredentials;
import com.app.ws.ui.model.response.AuthenticationDetails;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author philippgottschalk
 */
@Path("/authentication")
public class AuthenticationEntryPoint {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials){
        
        AuthenticationDetails returnValue = new AuthenticationDetails();
        
        AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
        UserDTO authenticatedUser = authenticationService.authenticate(loginCredentials.getPlayerId(), loginCredentials.getUserPassword());
        
        //Reset Acess Token
        authenticationService.resetSecurityCridentials(loginCredentials.getUserPassword(), authenticatedUser);
        
        
        String acessToken = authenticationService.issueAcessToken(authenticatedUser);
        
        returnValue.setId(authenticatedUser.getUserId());
        returnValue.setToken(acessToken);
        return returnValue;
    }
    
}
