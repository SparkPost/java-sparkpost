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

import java.util.Map;

/** DTO for storing the 'content' field in a template
 *
 * @author grava
 */
public class SPDTOTemplateContent extends SPDTOBase {

    public String html = null;
    public String text = null;
    public String subject = null;
    public SPDTOAddress from = null;
    public String reply_to = null;
    public Map<String, String> headers = null;

    /** Alternatively, the email_rfc822 may be used *instead* of all the other fields.
     *  The email_rfc822 field is mutually exclusive with all of the above fields.
     */
    public String email_rfc822 = null;

}
