package com.sparkpost.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionBase extends Base {

	public final static class TransmissionStates {
		public static final String SUBMITTED = "submitted";
		public static final String GREETING = "Generating";
		public static final String SUCCESS = "Success";
		public static final String CANCELED = "Canceled";
	}

	/**
	 * ID of the transmission
	 * 
	 * A unique ID is generated for each transmission on submission.
	 */
	private String id = null;

	/**
	 * State of the transmission
	 * 
	 * Valid responses are "submitted", "Generating", "Success", or "Canceled".
	 * 
	 * See TransmissionStates
	 */
	private String state = null;

	/**
	 * Object in which transmission options are defined
	 * 
	 * For a full description, see the Options Attributes.
	 */
	private OptionsAttributes options = null;

	/**
	 * Name of the campaign
	 * 
	 * Maximum length - 64 bytes
	 */
	@SerializedName("campaign_id")
	private String campaignId = null;

	/**
	 * Description of the transmission
	 * 
	 * Maximum length - 1024 bytes
	 */
	private String description = null;

	/**
	 * Transmission level metadata containing key/value pairs
	 * 
	 * Metadata is available during events through the Webhooks and is provided
	 * to the substitution engine. A maximum of 200 bytes of merged metadata
	 * (transmission level + recipient level) is available with recipient
	 * metadata taking precedence over transmission metadata when there are
	 * conflicts.
	 */
	private Map<String, String> metadata = null;

	/**
	 * Key/value pairs that are provided to the substitution engine
	 * 
	 * Recipient substitution data takes precedence over transmission
	 * substitution data. Unlike metadata, substitution data is not included in
	 * Webhook events.
	 */
	@SerializedName("substitution_data")
	private Map<String, String> substitutionData = null;

	/**
	 * Email to use for envelope FROM ( Note: SparkPost Elite only )
	 * 
	 * To support Variable Envelope Return Path (VERP), this field can also
	 * optionally be specified inside of the address object of a specific
	 * recipient in order to give the recipient a unique envelope MAIL FROM.
	 */
	@SerializedName("return_path")
	private String returnPath = null;

	/**
	 * Content that will be used to construct a message
	 * 
	 * Specify a stored template or specify inline template content. When using
	 * a stored template, specify the "template_id" as described in Using a
	 * Stored Template. Otherwise, provide the inline content using the fields
	 * described in the Templates API documentation for Content Attributes.
	 * Maximum size - 15MBs
	 */
	@SerializedName("content")
	private TemplateContentAttributes contentAttributes = null;

	/**
	 * Computed total recipients
	 */
	@SerializedName("total_recipients")
	@Getter private Integer totalRecipients = null;

	/**
	 * Computed total number of messages generated
	 */
	@SerializedName("num_generated")
	private Integer numGenerated = null;

	/**
	 * Computed total number of failed messages
	 */
	@SerializedName("num_failed_generation")
	private Integer numFailedGeneration = null;

	/**
	 * Number of recipients that failed input validation
	 */
	@SerializedName("num_invalid_recipients")
	private Integer numInvalidRecipients = null;
}
