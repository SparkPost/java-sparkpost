package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The response for the SparkPost server, as returned by @a RestConnection
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Response extends Base{

	private String request = null;
	
	private String requestId = null;
	
	private int responseCode = -1;
	
	private String responseMessage = null;
	
	@SerializedName("json")
	private String responseBody = null;


	public void reset() {
		request = null;
		requestId = null;
		responseCode = -1;
		responseMessage = null;
		responseBody = null;
	}

}
