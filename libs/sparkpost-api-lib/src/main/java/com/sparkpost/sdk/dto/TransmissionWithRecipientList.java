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
 * DTO for a transmission using a recipient list id (a recipient list stored at
 * the server)
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionWithRecipientList extends TransmissionBase {

	/**
	 * Specify a stored recipient list or specify recipients inline. When using
	 * a stored recipient list, specify the "list_id" as described in Using a
	 * Stored Recipient List. Otherwise, provide the recipients inline using the
	 * fields described in the Recipient List API documentation for Recipient
	 * Attributes.
	 */
	@SerializedName("recipients")
	private StoredRecipientList recipientList = null;

}
