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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Note: The Sending Domains API is available for SparkPost only.
 * 
 * A sending domain is a domain that is used to indicate who an email is from via the
 * "From:" header. Using a custom sending domain enables you to control what
 * recipients see as the From value in their email clients. DNS records can be
 * configured for a sending domain, which allows recipient mail servers to
 * authenticate your messages. The Sending Domains API provides the means to
 * create, list, retrieve, update, and verify a custom sending domain.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendingDomain extends Base {

	/**
	 * Name of the sending domain
	 * 
	 * The domain name will be used as the "From:" header address in the email.
	 */
	private String domain;

	/**
	 * DKIM key configuration
	 * 
	 * For a full description, see the DKIM Attributes.
	 */
	private DKIM dkim;

}
