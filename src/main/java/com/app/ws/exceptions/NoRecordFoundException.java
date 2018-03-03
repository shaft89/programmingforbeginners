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
public class NoRecordFoundException extends RuntimeException{

    private static final long serialVersionUID = 5211323089230555561L;


    
    public NoRecordFoundException(String message){
    
        super(message);
    }

    
}
