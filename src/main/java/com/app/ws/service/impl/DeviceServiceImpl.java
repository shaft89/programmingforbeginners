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
import com.app.ws.service.DeviceService;
import com.app.ws.shared.dto.DeviceDTO;
import com.app.ws.ui.model.response.ErrorMessages;

/**
 *
 * @author SBo
 */
public class DeviceServiceImpl implements DeviceService {
    DAO database;
    
    public DeviceServiceImpl(){
    this.database = new MySQLDAO();
    }
    
    public DeviceDTO getDevice(long id) {
        DeviceDTO returnValue = null;
        try{
            this.database.openConnection();
            returnValue = this.database.getDevice(id);
        } catch(Exception ex){
            ex.printStackTrace(); // SBO: Was macht die Zeile?
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()); 
        }finally{
            this.database.closeConnection();
        }
        return returnValue;
    }

    public DeviceDTO createDevice(DeviceDTO device) {
      
        //check if device exists
        /*DeviceDTO exisitingDevice = this.getDevice(device.getId());
        if(exisitingDevice != null){
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALLREADY_EXIST.name());
        }*/
 
        //record Date into a database
        DeviceDTO returnValue;
        returnValue = this.saveDevice(device);

        //Return back the device profile
        return returnValue;
    }
    
    private DeviceDTO saveDevice(DeviceDTO device){
        DeviceDTO returnValue = null;
         // Connect to database 
        try {
            this.database.openConnection();
            returnValue = this.database.saveDevice(device);
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    
    }
}
