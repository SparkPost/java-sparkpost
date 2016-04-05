package com.sparkpost.exception;

public class SparkPostAccessForbiddenException extends SparkPostException {

    private static final long serialVersionUID = 4127644290615861545L;

    private static final String MESSAGE = "API key has no required permission to perform this action.";

    public SparkPostAccessForbiddenException() {
        super(MESSAGE);
    }

}
