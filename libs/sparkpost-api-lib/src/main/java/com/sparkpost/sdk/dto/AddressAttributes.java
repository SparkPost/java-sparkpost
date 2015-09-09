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
 * DTO for storing an address (email, name, header_to)
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddressAttributes extends Base {

	public AddressAttributes() {
		
	}
	
	public AddressAttributes(String email) {
		this.email = email;
	}
	
	public AddressAttributes(String email, String name, String headerTo) {
		this.email = email;
		this.name = name;
		this.headerTo = headerTo;
	}
	
	
	/**
	 * Valid email address
	 */
	private String email;
	
	/**
	 * User-friendly name for the email address
	 */
	private String name;
	
	/**
	 * Email address to display in the "To" header instead of address.email (for BCC)
	 */
	@SerializedName("header_to")
	private String headerTo;

}
