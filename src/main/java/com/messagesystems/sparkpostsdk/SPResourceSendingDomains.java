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

/** Resource collection that is a 1-to-1 match to the Sending Domains SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/sending-domains/">Sending Domains API</a>
 *
 * @author grava
 */
public class SPResourceSendingDomains {

    static public SPResponse create(
            SPRestConn conn, SPDTOSendingDomain domain)
            throws SparkpostSdkException {

        String json = domain.toJson();
        return conn.post("sending-domains", json);
    }

    static public SPResponse retrieve(
            SPRestConn conn, String domainName)
            throws SparkpostSdkException {

        return conn.get("sending-domains/" + domainName);
    }

    static public SPResponse list(
            SPRestConn conn)
            throws SparkpostSdkException {

        return conn.get("sending-domains/");
    }

    static public SPResponse update(
            SPRestConn conn, String domainName, SPDTOSendingDomain domain)
            throws SparkpostSdkException {

        String json = domain.toJson();
        return conn.put("sending-domains/" + domainName, json);
    }

    static public SPResponse verify(
            SPRestConn conn, String domainName, SPDTOVerifySendingDomain verify)
            throws SparkpostSdkException {

        String json = verify.toJson();
        return conn.post("sending-domains/" + domainName + "/verify", json);
    }
}
