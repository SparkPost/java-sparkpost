package com.sparkpost.sdk.exception;

/**
 *
 * @author grava
 */
public class SparkpostSdkException extends Exception {

	private static final long serialVersionUID = 4017633480414265142L;

	public SparkpostSdkException() {
	}

	public SparkpostSdkException(String message) {
		super(message);
	}

	public SparkpostSdkException(Throwable cause) {
		super(cause);
	}

	public SparkpostSdkException(String message, Throwable cause) {
		super(message, cause);
	}
}
