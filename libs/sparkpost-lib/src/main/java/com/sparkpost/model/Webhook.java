package com.sparkpost.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

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

	@Description(value="")
	private String name;
	
	@Description(value="")
	private String target;
	
	@Description(value="")
	@SerializedName("auth_token")
	private String authToken;
	
	@Description(value="")
	private List<String> events;

}
