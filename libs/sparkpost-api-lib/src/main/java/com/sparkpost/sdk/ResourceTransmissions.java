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

package com.sparkpost.sdk;

import com.sparkpost.sdk.dto.TransmissionWithRecipientArray;

/** Resource collection that is a 1-to-1 match to the Transmissions SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/transmissions/">Transmissions API</a>
 *
 * @author grava
 */
public class ResourceTransmissions {

    static public Response create(
            RestConn conn, Integer numRcptErrors, TransmissionWithRecipientArray trans)
            throws SparkpostSdkException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("num_rcpt_errors", numRcptErrors);
        String json = trans.toJson();
        return conn.post(ep.toString(), json);
    }

    static public Response retrieve(
            RestConn conn, String id)
            throws SparkpostSdkException {

        return conn.get("transmissions/" + id);
    }

    static public Response list(
            RestConn conn, String campaignId, String templateId) 
            throws SparkpostSdkException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("campaign_id", campaignId);
        ep.addParam("template_id", templateId);
        return conn.get(ep.toString());
    }
}
