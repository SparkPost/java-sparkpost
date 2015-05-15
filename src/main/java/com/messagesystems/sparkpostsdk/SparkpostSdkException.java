/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

/**
 *
 * @author grava
 */
public class SparkpostSdkException extends Exception {
    // Parameterless Constructor
    public SparkpostSdkException() {}
    
    // Constructor that accepts a message
    public SparkpostSdkException( String message )
    {
        super( message ) ;
    }
    
        public SparkpostSdkException( Throwable cause ) {
        super ( cause ) ;
    }
        
    public SparkpostSdkException( String message, Throwable cause ) {
        super (message, cause ) ;
    }
}
