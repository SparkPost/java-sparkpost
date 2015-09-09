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

import com.sparkpost.sdk.dto.RecipientList;

/** Resource collection that is a 1-to-1 match to the Recipient Lists SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/recipient-lists/">Recipient Lists API</a>
 * 
 * @author grava
 */
public class ResourceRecipientLists {

    static public Response create(
            RestConn conn, Integer maxNumberOfRecipientErrors, RecipientList recipientList)
            throws SparkpostSdkException {
        String json = recipientList.toJson();
        Endpoint ep = new Endpoint("recipient-lists");
        ep.addParam("num_rcpt_errors", maxNumberOfRecipientErrors);
        return conn.post(ep.toString(), json);
    }

    static public Response retrieve(
            RestConn conn, String recipientListId, Boolean showRecipients)
            throws SparkpostSdkException {
        Endpoint ep = new Endpoint("recipient-lists/" + recipientListId);
        ep.addParam("show_recipients", showRecipients);
        return conn.get(ep.toString());
    }

    static public Response listAll(RestConn conn) throws SparkpostSdkException {
        return conn.get("recipient-lists");
    }

    static public Response delete(RestConn conn, String recipientListId) throws SparkpostSdkException {
        String path = "recipient-lists/" + recipientListId;
        return conn.delete(path);
    }
}
