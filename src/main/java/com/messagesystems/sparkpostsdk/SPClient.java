/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author grava
 */
public class SPClient {

    private static final Logger logger = Logger.getLogger(SPClient.class);
    private String authKey = null;
    private String fromEmail = null ;

    public SPClient() {
    }

    public SPClient(String key) {
        SetAuthKey(key);
    }

    /**
     *
     * @param key
     */
    public final void SetAuthKey(String key) {
        this.authKey = key;
        logger.debug("Auth key now: " + this.authKey);
    }

    public String GetAuthKey() {
        return this.authKey;
    }

    /**
     * @return the fromEmail
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * @param fromEmail the fromEmail to set
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    
    @Override
    public String toString() {
        return "Auth key: " + this.authKey + ", From Email: " + this.fromEmail ;
    }

}
