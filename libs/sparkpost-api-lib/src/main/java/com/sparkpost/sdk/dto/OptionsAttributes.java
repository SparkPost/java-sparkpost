package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing the 'options' field in a template.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionsAttributes extends Base {

	/**
	 * Delay generation of messages until this datetime. For additional information, see Scheduled Transmissions.
	 * 
	 * defaults to immediate generation
	 * 
	 * Format YYYY-MM-DDTHH:MM:SS+-HH:MM or "now". Example: '2015-02-11T08:00:00-04:00'.
	 */
	@SerializedName("start_time")
	private String startTime;
	
	/**
	 * Whether open tracking is enabled for this transmission
	 * 
	 * If not specified, the setting at template level is used, or defaults to
	 * true.
	 */
	@SerializedName("open_tracking")
	private Boolean openTracking;

	/**
	 * Whether click tracking is enabled for this transmission
	 * 
	 * If not specified, the setting at template level is used, or defaults to
	 * true.
	 */
	@SerializedName("click_tracking")
	private Boolean clickTracking = true;

	/**
	 * Whether message is transactional or non-transactional for unsubscribe and
	 * suppression purposes
	 * 
	 * If not specified, the setting at template level is used, or defaults to
	 * false.
	 */
	private Boolean transactional = false;

	/**
	 * Whether or not to use the sandbox sending domain ( Note: SparkPost only )
	 */
	private Boolean sandbox = false;

	/**
	 * Unlike most other options, this flag is omitted on a GET transmission
	 * response unless the flag's value is true.
	 * 
	 * Whether or not to ignore customer suppression rules, for this
	 * transmission only. Only applicable if your configuration supports this
	 * parameter. ( Note: SparkPost Elite only )
	 */
	@SerializedName("skip_suppression")
	private Boolean skipSuppression;
}
