package com.sparkpost.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The response for the SparkPost server, as returned by @a RestConnection
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Response extends Base {

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
	
	public static Response decode(Response response, Class<?> clazz) {
		Gson gson = new Gson();
		
		Response newReponse = (Response) gson.fromJson(response.getResponseBody(), clazz);
		if (newReponse != null) {
			newReponse.request = response.request;
			newReponse.requestId = response.requestId;
			newReponse.responseCode = response.responseCode;
			newReponse.responseBody = response.responseBody;
			newReponse.responseMessage = response.responseMessage;
		}
		
		return newReponse;
	}

}
