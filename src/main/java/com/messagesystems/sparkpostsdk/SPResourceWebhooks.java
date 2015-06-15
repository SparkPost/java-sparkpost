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

/** Resource collection that is a 1-to-1 match to the Webhooks SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/webhooks/">Webhooks API</a>
 *
 * @author grava
 */
public class SPResourceWebhooks {

    static public SPResponse listSampleValuesAndEvents(
            SPRestConn conn)
            throws SparkpostSdkException {

        return conn.get("webhooks/events/documentation");
    }

    static public SPResponse getSamplePayloadForEvents(
            SPRestConn conn, String events)
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("webhooks/events/samples");
        ep.addParam("events", events);
        return conn.get(ep.toString());
    }

    static public SPResponse listAll(
            SPRestConn conn, String timezone)
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("webhooks");
        ep.addParam("timezone", timezone);
        return conn.get(ep.toString());
    }

    static public SPResponse create(
            SPRestConn conn, SPDTOWebhook webhook)
            throws SparkpostSdkException {

        String json = webhook.toJson();
        return conn.post("webhooks", json);
    }

    static public SPResponse describe(
            SPRestConn conn, String id, String timezone) 
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("webhooks/" + id);
        ep.addParam("timezone", timezone);
        return conn.get(ep.toString());
    }

    static public SPResponse update(
            SPRestConn conn, String id, SPDTOWebhook webhook)
            throws SparkpostSdkException {
     
        String json = webhook.toJson();
        return conn.post("webhooks/"+id, json);
    }

    static public SPResponse delete(
            SPRestConn conn, String id ) 
            throws SparkpostSdkException {

        return conn.delete("webhooks/"+id);
    }
}
