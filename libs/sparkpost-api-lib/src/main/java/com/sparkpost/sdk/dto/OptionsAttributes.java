/* Copyright 2014 Message Systems, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this software except in compliance with the License.
 *
 * A copy of the License is located at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * or in the "license" file accompanying this software. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing the 'options' field in a template.
 *
 * @author grava
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
