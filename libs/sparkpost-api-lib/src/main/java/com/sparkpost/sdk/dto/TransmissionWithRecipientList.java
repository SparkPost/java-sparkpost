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

/** DTO for a transmission using a recipient list id (a recipient list stored at
 * the server)
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TransmissionWithRecipientList extends Base {

	private String id = null;
	
	private String state = null;
	
	private TemplateOptions options = null;

    @SerializedName("recipients")
    private StoredRecipientList recipientList = null;

    private String campaign_id = null;
    
    private String description = null;
    
    private Map<String, String> metadata = null;
    
    private Map<String, String> substitution_data = null;
    
    private String return_path = null;

    @SerializedName("content")
    private StoredTemplate storedTemplate = null;

    private Integer total_recipients = null;
    
    private Integer num_generated = null;
    
    private Integer num_failed_generation = null;
    
    private Integer num_invalid_recipients = null;

}
