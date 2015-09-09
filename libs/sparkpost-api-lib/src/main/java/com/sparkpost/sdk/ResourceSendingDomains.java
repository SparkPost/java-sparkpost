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

import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.SendingDomain;
import com.sparkpost.sdk.dto.SparkpostSdkException;
import com.sparkpost.sdk.dto.VerifyAttributes;

/**
 * Resource collection that is a 1-to-1 match to the Sending Domains SparkPost
 * API.<br>
 * <br>
 * See
 * <a href="https://www.sparkpost.com/api#/reference/sending-domains/">Sending
 * Domains API</a>
 *
 * @author grava
 */
public class ResourceSendingDomains {

	public static Response create(RestConnection conn, SendingDomain domain) throws SparkpostSdkException {

		String json = domain.toJson();
		return conn.post("sending-domains", json);
	}

	public static Response retrieve(RestConnection conn, String domainName) throws SparkpostSdkException {

		return conn.get("sending-domains/" + domainName);
	}

	public static Response list(RestConnection conn) throws SparkpostSdkException {

		return conn.get("sending-domains/");
	}

	public static Response update(RestConnection conn, String domainName, SendingDomain domain) throws SparkpostSdkException {

		String json = domain.toJson();
		return conn.put("sending-domains/" + domainName, json);
	}

	public static Response verify(RestConnection conn, String domainName, VerifyAttributes verify)
			throws SparkpostSdkException {

		String json = verify.toJson();
		return conn.post("sending-domains/" + domainName + "/verify", json);
	}
}
