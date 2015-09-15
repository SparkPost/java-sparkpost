package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateItem extends Base {
	
	private String name;
	
	private String id;
	
	@SerializedName("last_update_time")
	private String lastUpdateTime;
	
	private String description;
	
	private Boolean published;
}
