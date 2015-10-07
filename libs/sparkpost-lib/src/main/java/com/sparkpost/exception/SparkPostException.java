
package com.sparkpost.exception;

/**
 * @author grava
 */
public class SparkPostException extends Exception {

    private static final long serialVersionUID = 4017633480414265142L;

    public SparkPostException() {
    }

    public SparkPostException(String message) {
        super(message);
    }

    public SparkPostException(Throwable cause) {
        super(cause);
    }

    public SparkPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
