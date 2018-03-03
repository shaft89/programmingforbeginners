/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.service;

import com.app.ws.shared.dto.UserDTO;

/**
 *
 * @author philippgottschalk
 */
public interface UsersService {
    UserDTO createUser(UserDTO user);
    UserDTO getUser(String id);
    UserDTO getUserByPlayerID(String playerId);
    
}
