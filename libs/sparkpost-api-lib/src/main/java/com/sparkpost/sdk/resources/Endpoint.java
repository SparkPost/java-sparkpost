package com.sparkpost.sdk.resources;

import org.apache.http.client.utils.URIBuilder;

/**
 * Used internally to the SparkPost SDK to write URL queries.
 *
 * @author grava
 */
public class Endpoint {

	private URIBuilder uriBuilder;

	public Endpoint(String endpoint) {
		uriBuilder = new URIBuilder();
		uriBuilder.setPath(endpoint);
	}

	private void addString(String name, String value) {
		uriBuilder.addParameter(name, value);
	}

	public Endpoint addParam(String name, String val) {
		if (val != null) {
			addString(name, val);
		}
		return this;
	}

	public Endpoint addParam(String name, Integer value) {
		if (value == null) {
			return this;
		}
		uriBuilder.addParameter(name, value.toString());
		return this;
	}

	public Endpoint addParam(String name, Boolean value) {
		uriBuilder.addParameter(name, value.toString());
		return this;
	}

	@Override
	public String toString() {
		return uriBuilder.toString();
	}

}
