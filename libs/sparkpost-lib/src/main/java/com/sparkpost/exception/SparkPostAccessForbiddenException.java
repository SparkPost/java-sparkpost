
package com.sparkpost.exception;

public class SparkPostAccessForbiddenException extends SparkPostException {

    private static final long serialVersionUID = 4127644290615861545L;

    private static final String MESSAGE = "API key has no required permission to perform this action.";

    private final String serverMessage;

    public SparkPostAccessForbiddenException() {
        super(MESSAGE);
        this.serverMessage = MESSAGE;
    }

    public SparkPostAccessForbiddenException(String serverMessage) {
        super(MESSAGE);
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return this.serverMessage;
    }
}
