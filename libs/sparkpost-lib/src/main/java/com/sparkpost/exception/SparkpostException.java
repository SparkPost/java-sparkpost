package com.sparkpost.exception;

/**
 *
 * @author grava
 */
public class SparkpostException extends Exception {

	private static final long serialVersionUID = 4017633480414265142L;

	public SparkpostException() {
	}

	public SparkpostException(String message) {
		super(message);
	}

	public SparkpostException(Throwable cause) {
		super(cause);
	}

	public SparkpostException(String message, Throwable cause) {
		super(message, cause);
	}
}
