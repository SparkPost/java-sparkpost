package com.sparkpost.sdk.dto;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a recipient.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientAttributes extends Base {

	/**
	 * Address information for a recipient
	 */
	private AddressAttributes address = null;

	/**
	 * Email to use for envelope FROM ( Note: SparkPost Elite only )
	 * 
	 * To support Variable Envelope Return Path (VERP), this field provides a
	 * specific recipient a unique envelope MAIL FROM.
	 */
	@SerializedName("return_path")
	private String returnPath = null;

	/**
	 * Array of text labels associated with a recipient
	 * 
	 * Tags are available in Webhook events. Maximum number of tags - 10 per
	 * recipient, 100 system wide. Any tags over the limits are ignored.
	 */
	private List<String> tags = null;

	/**
	 * Key/value pairs associated with a recipient
	 * 
	 * Metadata is available during events through the Webhooks and is provided
	 * to the substitution engine. A maximum of 200 bytes of merged metadata
	 * (transmission level + recipient level) is available with recipient
	 * metadata taking precedence over transmission metadata when there are
	 * conflicts.
	 */
	private Map<String, String> metadata = null;

	/**
	 * Key/value pairs associated with a recipient that are provided to the
	 * substitution engine
	 * 
	 * Recipient substitution data takes precedence over transmission
	 * substitution data. Unlike metadata, substitution data is not included in
	 * Webhook events.
	 */
	@SerializedName("substitution_data")
	private Map<String, String> substitutionData = null;

}
