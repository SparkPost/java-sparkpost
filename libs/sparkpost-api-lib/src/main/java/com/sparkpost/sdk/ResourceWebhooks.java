package com.sparkpost.sdk;

import com.sparkpost.sdk.dto.Response;
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
