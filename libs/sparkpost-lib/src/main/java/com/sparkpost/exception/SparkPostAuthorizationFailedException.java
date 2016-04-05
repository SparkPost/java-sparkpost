package com.sparkpost.exception;

public class SparkPostAuthorizationFailedException extends SparkPostException {

    private static final long serialVersionUID = 3695537080454452130L;

    private static final String MESSAGE = "Authorization failed. Check your API key.";

    public SparkPostAuthorizationFailedException() {
        super(MESSAGE);
    }
}
