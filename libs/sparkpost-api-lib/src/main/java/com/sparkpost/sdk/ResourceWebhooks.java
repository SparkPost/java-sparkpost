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
import com.sparkpost.sdk.dto.Webhook;

/**
 * Resource collection that is a 1-to-1 match to the Webhooks SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/webhooks/">Webhooks
 * API</a>
 *
 * @author grava
 */
public class ResourceWebhooks {

	public static Response listSampleValuesAndEvents(RestConnection conn) throws SparkpostSdkException {

		return conn.get("webhooks/events/documentation");
	}

	public static Response getSamplePayloadForEvents(RestConnection conn, String events) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("webhooks/events/samples");
		ep.addParam("events", events);
		return conn.get(ep.toString());
	}

	public static Response listAll(RestConnection conn, String timezone) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("webhooks");
		ep.addParam("timezone", timezone);
		return conn.get(ep.toString());
	}

	public static Response create(RestConnection conn, Webhook webhook) throws SparkpostSdkException {

		String json = webhook.toJson();
		return conn.post("webhooks", json);
	}

	public static Response describe(RestConnection conn, String id, String timezone) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("webhooks/" + id);
		ep.addParam("timezone", timezone);
		return conn.get(ep.toString());
	}

	public static Response update(RestConnection conn, String id, Webhook webhook) throws SparkpostSdkException {

		String json = webhook.toJson();
		return conn.post("webhooks/" + id, json);
	}

	public static Response delete(RestConnection conn, String id) throws SparkpostSdkException {

		return conn.delete("webhooks/" + id);
	}
}
