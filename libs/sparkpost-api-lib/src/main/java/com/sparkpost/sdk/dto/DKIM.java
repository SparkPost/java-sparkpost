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
 * DKIM uses a pair of public and private keys to authenticate your emails.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DKIM extends Base {

	/**
	 * DKIM private key
	 * 
	 * The private key will be used to create the DKIM Signature.
	 */
	@SerializedName("private")
	private String privateKey;

	/**
	 * DKIM public key
	 * 
	 * The public key will be retrieved from DNS of the sending domain.
	 */
	@SerializedName("public")
	private String publicKey;

	/**
	 * DomainKey selector
	 * 
	 * The DomainKey selector will be used to indicate the DKIM public key location.
	 */
	private String selector;

	/**
	 * Header fields to be included in the DKIM signature
	 * 
	 * Header fields are separated by a colon. Example: "from:to:subject:date"
	 */
	private String headers;
}
