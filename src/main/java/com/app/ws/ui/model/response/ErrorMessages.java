/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.ui.model.response;

/**
 *
 * @author philippgottschalk
 */
public enum ErrorMessages {
    
    RECORD_ALLREADY_EXIST("Eintrag existiert bereits!"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("Kein Eintrag gefunden!"),
    AUTHENTICATION_FAILED("Login fehlgeschlagen");
    
    
    
    private String errorMessage;
    
     ErrorMessages(String errorMessage){
        this.errorMessage = errorMessage;  
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
