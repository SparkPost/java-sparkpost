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

import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** DTO for storing the 'content' field in a template
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateContentAttributes extends Base {

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
