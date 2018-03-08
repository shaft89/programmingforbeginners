/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.ui.entrypoint;

import com.app.ws.service.DeviceService;
import com.app.ws.service.GameService;
import com.app.ws.service.UsersService;
import com.app.ws.service.impl.DeviceServiceImpl;
import com.app.ws.service.impl.GameServiceImpl;
import com.app.ws.service.impl.UserServiceImpl;
import com.app.ws.shared.dto.DeviceDTO;
import com.app.ws.shared.dto.GameDTO;
import com.app.ws.shared.dto.UserDTO;
import com.app.ws.ui.model.request.CreateGameRequestModel;
import com.app.ws.ui.model.request.CreateUserRequestModel;
import com.app.ws.ui.model.response.DeviceProfileRest;
import com.app.ws.ui.model.response.GameProfileRest;
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
@Path("/game")
public class GameEntryPoint {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public GameProfileRest createUser(CreateGameRequestModel requestObject){
        GameProfileRest returnValue = new GameProfileRest();
        
        //Prepare GameDTO
        GameDTO gameDto = new GameDTO();
        BeanUtils.copyProperties(requestObject, gameDto);
        
        //Create new Game
        GameService gameService = new GameServiceImpl();
        GameDTO createdGameProfile = gameService.createGame(gameDto);
        
        //Prepare Response
        BeanUtils.copyProperties(createdGameProfile, returnValue);
        return returnValue;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public GameProfileRest getDeviceProfile(@PathParam("id") String id){
        
        GameProfileRest returnValue = null;
        
        GameService gameService = new GameServiceImpl();
        GameDTO gameProfile = gameService.getGame(id);
                
        returnValue = new GameProfileRest();
        BeanUtils.copyProperties(gameProfile, returnValue);
        
        return returnValue;
    }
    
}
