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
public class AuthenticationException extends RuntimeException{

    private static final long serialVersionUID = 3308690569206444574L;
    
    public AuthenticationException(String message){
        super(message);
    }
    
    
}
