package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a stored template.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StoredTemplate extends Base {

	/**
	 * ID of the stored template to use
	 * 
	 * Specify this field when using a stored template. Maximum length -- 64
	 * bytes
	 */
	@SerializedName("template_id")
	private String templateId;

	/**
	 * Whether or not to use a draft template
	 * 
	 * If this field is set to true and no draft template exists, the
	 * transmission will fail.
	 */
	@SerializedName("use_draft_template")
	private Boolean useDraftTemplate;
}
