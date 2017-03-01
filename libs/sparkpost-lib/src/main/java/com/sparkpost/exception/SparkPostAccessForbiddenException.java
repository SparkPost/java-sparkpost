
package com.sparkpost.exception;

import com.sparkpost.model.responses.ServerErrorResponses;

public class SparkPostAccessForbiddenException extends SparkPostException {

    private static final long serialVersionUID = 4127644290615861545L;

    private static final String MESSAGE = "API key does not have the required permission to perform this action.";

    private final String serverMessage;

    public SparkPostAccessForbiddenException() {
        super(MESSAGE);
        this.serverMessage = MESSAGE;
    }

    public SparkPostAccessForbiddenException(String serverMessage) {
        super(MESSAGE);
        this.serverMessage = serverMessage;
    }

    public SparkPostAccessForbiddenException(String serverMessage, ServerErrorResponses errorResponses) {
        super(MESSAGE, errorResponses);
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return this.serverMessage;
    }
}
