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
import com.sparkpost.sdk.dto.SparkpostSdkException;
import com.sparkpost.sdk.dto.SuppressionList;
import com.sparkpost.sdk.dto.SuppressionListEntry;

/**
 * Resource collection that is a 1-to-1 match to the Suppression List SparkPost
 * API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/suppression-list/">
 * Suppression List API</a>
 *
 * @author grava
 */
public class ResourceSuppressionList {

	public static Response insertOrUpdate(RestConn conn, String email, final SuppressionListEntry entry)
			throws SparkpostSdkException {

		// we need a copy because we don't want the email field in the json:
		SuppressionListEntry copy = new SuppressionListEntry(entry);
		copy.setEmail(null);

		String json = copy.toJson();
		return conn.put("suppression-list/" + email, json);
	}

	public static Response insertOrUpdateBulk(RestConn conn, SuppressionList suppressionList)
			throws SparkpostSdkException {

		String json = suppressionList.toJson();
		return conn.put("suppression-list/", json);
	}

	public static Response search(RestConn conn, String to, String from, String types, String limit)
			throws SparkpostSdkException {

		Endpoint ep = new Endpoint("suppression-list");
		ep.addParam("to", to);
		ep.addParam("from", from);
		ep.addParam("types", types);
		ep.addParam("limit", limit);
		return conn.get(ep.toString());
	}

	public static Response check(RestConn conn, String email) throws SparkpostSdkException {

		return conn.get("suppression-list/" + email);
	}

	public static Response remove(RestConn conn, String email) throws SparkpostSdkException {

		return conn.delete("suppression-list/" + email);
	}
}
