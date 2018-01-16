/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.request.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement 
public class SuccessOrFailMessage {
    private String message; 
    
    public void setSuccess(){
        setMessage("success"); 
    }
    
    public void setFailure(){
        setMessage("failure"); 
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
