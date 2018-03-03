/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.ui.entrypoint;

import com.app.ws.annotaions.Secured;
import com.app.ws.service.UsersService;
import com.app.ws.service.impl.UserServiceImpl;
import com.app.ws.shared.dto.UserDTO;
import com.app.ws.ui.model.request.CreateUserRequestModel;
import com.app.ws.ui.model.response.UserProfileRest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author philippgottschalk
 */
@Path("/users")
public class UsersEntryPoint {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest createUser(CreateUserRequestModel requestObject){
        UserProfileRest returnValue = new UserProfileRest();
        
        //Prepare UserDTO
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(requestObject, userDto);
        
        //Create new User
        UsersService userService = new UserServiceImpl();
        UserDTO createdUserProfile = userService.createUser(userDto);
        
        //Prepare Response
        BeanUtils.copyProperties(createdUserProfile, returnValue);
        return returnValue;
  
    }
    
    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest getUserProfile(@PathParam("id") String id){
        
        UserProfileRest returnValue = null;
        
        UsersService userService = new UserServiceImpl();
        UserDTO userProfile = userService.getUser(id);
                
        returnValue = new UserProfileRest();
        BeanUtils.copyProperties(userProfile, returnValue);
        
        return returnValue;
    }
    
    @GET
    @Path("/player/{playerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest getUserByPlayerId(@PathParam("playerId") String playerId){
        
        UserProfileRest returnValue = null;
        
        UsersService userService = new UserServiceImpl();
        UserDTO userProfile = userService.getUserByPlayerID(playerId);
                
        returnValue = new UserProfileRest();
        BeanUtils.copyProperties(userProfile, returnValue);
        
        return returnValue;
    }
    
    
    
}
