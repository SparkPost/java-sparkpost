package com.sparkpost.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing info about a webhook.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Webhook extends Base {

	private String name;
	
	private String target;
	
	@SerializedName("auth_token")
	private String authToken;
	
	private List<String> events;

}
