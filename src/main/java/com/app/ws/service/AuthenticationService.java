/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.service;

import com.app.ws.exceptions.AuthenticationException;
import com.app.ws.shared.dto.UserDTO;

/**
 *
 * @author philippgottschalk
 */
public interface AuthenticationService {
    UserDTO authenticate(String serName, String password) throws AuthenticationException;
    String issueAcessToken(UserDTO userProfile) throws AuthenticationException;
    void resetSecurityCridentials(String password, UserDTO userProfile);
    
}
