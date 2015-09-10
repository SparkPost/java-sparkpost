package com.sparkpost.sdk.dto;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing substitution data (list of key=value).
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateSubstitutionData extends Base {
	
	@SerializedName("substitution_data")
	private Map<String, String> substitutionData;

}
