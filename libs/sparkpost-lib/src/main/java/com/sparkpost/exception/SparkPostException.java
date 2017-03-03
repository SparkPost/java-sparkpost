
package com.sparkpost.exception;

import com.sparkpost.model.responses.ServerErrorResponses;

public class SparkPostException extends Exception {

    private static final long serialVersionUID = 4017633480414265142L;

    private final ServerErrorResponses errorResponses;

    public SparkPostException() {
        this.errorResponses = null;
    }

    public SparkPostException(String message) {
        super(message);
        this.errorResponses = null;
    }

    public SparkPostException(String message, ServerErrorResponses errorResponses) {
        super(message);
        this.errorResponses = errorResponses;
    }

    public SparkPostException(Throwable cause) {
        super(cause);
        this.errorResponses = null;
    }

    public SparkPostException(Throwable cause, ServerErrorResponses errorResponses) {
        super(cause);
        this.errorResponses = errorResponses;
    }

    public SparkPostException(String message, Throwable cause) {
        super(message, cause);
        this.errorResponses = null;
    }

    public SparkPostException(String message, Throwable cause, ServerErrorResponses errorResponses) {
        super(message, cause);
        this.errorResponses = errorResponses;
    }

    public ServerErrorResponses getServerErrorResponses() {
        return this.errorResponses;
    }
}
