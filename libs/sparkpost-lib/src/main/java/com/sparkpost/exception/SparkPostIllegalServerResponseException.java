
package com.sparkpost.exception;

public class SparkPostIllegalServerResponseException extends SparkPostException {

    private static final long serialVersionUID = -1392320013310556792L;

    public SparkPostIllegalServerResponseException() {
    }

    public SparkPostIllegalServerResponseException(String message) {
        super(message);
    }

    public SparkPostIllegalServerResponseException(Throwable cause) {
        super(cause);
    }

    public SparkPostIllegalServerResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
