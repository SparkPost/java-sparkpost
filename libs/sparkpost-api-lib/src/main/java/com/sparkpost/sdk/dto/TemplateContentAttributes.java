package com.sparkpost.sdk.dto;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** DTO for storing the 'content' field in a template
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateContentAttributes extends Base {

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
		
	private String html = null;
	
	private String text = null;
	
	private String subject = null;
	
	private AddressAttributes from = null;
	
	@SerializedName("reply_to")
	private String replyTo = null;
	
	private Map<String, String> headers = null;

    /** 
     * Alternatively, the email_rfc822 may be used *instead* of all the other fields.
     *  The email_rfc822 field is mutually exclusive with all of the above fields.
     */
	@SerializedName("email_rfc822")
	private String emailRFC822 = null;

}
