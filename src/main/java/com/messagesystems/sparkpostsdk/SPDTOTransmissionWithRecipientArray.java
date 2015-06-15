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

package com.messagesystems.sparkpostsdk;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/** DTO for a transmission using an array of recipients.
 *
 * @author grava
 */
public class SPDTOTransmissionWithRecipientArray extends SPDTOBase {

    public String id = null;
    public String state = null;
    public SPDTOTemplateOptions options = null;

    @SerializedName("recipients")
    public SPDTORecipient[] recipientArray = null;

    public String campaign_id = null;
    public String description = null;
    public Map<String, String> metadata = null;
    public Map<String, String> substitution_data = null;
    public String return_path = null;

    @SerializedName("content")
    public SPDTOStoredTemplate storedTemplate = null;

    public Integer total_recipients = null;
    public Integer num_generated = null;
    public Integer num_failed_generation = null;
    public Integer num_invalid_recipients = null;

}
