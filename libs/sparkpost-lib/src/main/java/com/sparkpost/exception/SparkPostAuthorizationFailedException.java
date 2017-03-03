
package com.sparkpost.exception;

import com.sparkpost.model.responses.ServerErrorResponses;

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

    public SparkPostAuthorizationFailedException(String serverMessage, ServerErrorResponses errorResponses) {
        super(MESSAGE, errorResponses);
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return this.serverMessage;
    }
}
