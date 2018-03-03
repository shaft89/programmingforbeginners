/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.exceptions;

/**
 *
 * @author philippgottschalk
 */
public class CouldNotCreateRecordException extends RuntimeException{
    
    private static final long serialVersionUID = 8065041550471459355L;
    
    public CouldNotCreateRecordException(String message){
        super(message);
            }
    
}
