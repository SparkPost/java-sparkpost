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

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A recipient list is a collection of recipients that can be used in a
 * transmission. The Recipient List API provides the means to manage recipient
 * lists. When creating a new transmission using the Transmissions API, the
 * recipients may be submitted "inline" as part of the transmission data, or a
 * stored recipient list id attribute can be specified.The Recipient List API
 * operates on lists as a whole and does not currently support management of
 * individual recipients.Recipient List Attributes
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientList extends Base {

	/**
	 * Short, unique, recipient list identifier
	 * 
	 * If an id is not specified, one is generated. Maximum length - 64 bytes
	 */
	private String id = null;

	/**
	 * Short, pretty/readable recipient list display name, not required to be unique
	 * 
	 * If a name is not specified, then defaults to the same value as id. Maximum length - 64 bytes
	 */
	private String name = null;

	/**
	 * Detailed description of the recipient list
	 * 
	 * Maximum length - 1024 bytes
	 */
	private String description = null;

	/**
	 * Recipient list attribute object
	 * 
	 * This JSON object allows users to store arbitrary metadata related to this list. This data is not used by the API. It is only for the user.
	 */
	private Map<String, String> attributes = null;

	/**
	 * List of recipient objects
	 * 
	 * For a full description, see the Recipient Attributes.
	 */
	private List<RecipientAttributes> recipients = null;

}
