/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.ui.model.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SBo
 */
@XmlRootElement
public class DeviceProfileRest {
    private long id;
    private String name;
         
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the name to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }   
}
