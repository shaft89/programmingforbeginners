/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.ui.entrypoint;

import com.app.ws.annotaions.Secured;
import com.app.ws.service.DeviceService;
import com.app.ws.service.impl.DeviceServiceImpl;
import com.app.ws.shared.dto.DeviceDTO;
import com.app.ws.ui.model.request.CreateDeviceRequestModel;
import com.app.ws.ui.model.response.DeviceProfileRest;
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
 * @author SBo
 */
@Path("device")
public class DeviceEntryPoint {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeviceProfileRest createDevice(CreateDeviceRequestModel requestObject){
        DeviceProfileRest returnValue = new DeviceProfileRest();
        
        //Prepare DeviceDTO
        DeviceDTO deviceDto = new DeviceDTO();
        BeanUtils.copyProperties(requestObject, deviceDto);
        
        //Create new Device
        DeviceService deviceService = new DeviceServiceImpl();
        DeviceDTO createdDeviceProfile = deviceService.createDevice(deviceDto);
        
        //Prepare Response
        BeanUtils.copyProperties(createdDeviceProfile, returnValue);
        return returnValue;
  
    }
    
    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeviceProfileRest getDeviceProfile(@PathParam("id") String id){
        
        DeviceProfileRest returnValue = null;
        
        long value = Long.parseLong(id);
        DeviceService deviceService = new DeviceServiceImpl();
        DeviceDTO deviceProfile = deviceService.getDevice(value);
                
        returnValue = new DeviceProfileRest();
        BeanUtils.copyProperties(deviceProfile, returnValue);
        
        return returnValue;
    }
}
