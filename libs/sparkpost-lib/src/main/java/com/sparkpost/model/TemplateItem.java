package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateItem extends Base {
	
	@Description(value="The name of the template")
	private String name;
	
	@Description(value="The ID of the template")
	private String id;
	
	@Description(value="Format YYYY-MM-DDTHH:MM:SS+-HH:MM or \"now\". Example: '2015-02-11T08:00:00-04:00'.")
	@SerializedName("last_update_time")
	private String lastUpdateTime;
	
	@Description(value="Template Description")
	private String description;
	
	@Description(value="true if template is published otherwise it is a draft")
	private Boolean published;
}
