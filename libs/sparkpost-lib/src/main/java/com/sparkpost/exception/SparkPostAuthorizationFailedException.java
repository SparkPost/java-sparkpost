
package com.sparkpost.exception;

public class SparkPostAuthorizationFailedException extends SparkPostException {

    private static final long serialVersionUID = 3695537080454452130L;

    private static final String MESSAGE = "Authorization failed. Check your API key.";

    private final String serverMessage;

    public SparkPostAuthorizationFailedException() {
        super(MESSAGE);
        this.serverMessage = MESSAGE;
    }

    public SparkPostAuthorizationFailedException(String serverMessage) {
        super(MESSAGE);
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return this.serverMessage;
    }
}
