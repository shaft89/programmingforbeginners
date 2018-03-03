/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.io.dao;

import com.app.ws.shared.dto.UserDTO;

/**
 *
 * @author philippgottschalk
 */
public interface DAO {
    void openConnection();
    UserDTO getUserByPlayerID(String playerID);
    UserDTO saveUser(UserDTO user);
    UserDTO getUser(String id);
    void updateUser(UserDTO userProfile);
    void closeConnection();
    
    
    
}
