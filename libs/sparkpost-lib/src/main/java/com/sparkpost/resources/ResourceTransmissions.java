package com.sparkpost.resources;

import com.sparkpost.exception.SparkpostException;
import com.sparkpost.model.Response;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.transport.RestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Transmissions SparkPost
 * API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/transmissions/">
 * Transmissions API</a>
 *
 * @author grava
 */
public class ResourceTransmissions {

	public static Response create(RestConnection conn, Integer numRcptErrors, TransmissionWithRecipientArray trans)
			throws SparkpostException {

		Endpoint ep = new Endpoint("transmissions");
		ep.addParam("num_rcpt_errors", numRcptErrors);
		String json = trans.toJson();
		return conn.post(ep.toString(), json);
	}

	public static Response retrieve(RestConnection conn, String id) throws SparkpostException {

		return conn.get("transmissions/" + id);
	}

	public static Response list(RestConnection conn, String campaignId, String templateId) throws SparkpostException {

		Endpoint ep = new Endpoint("transmissions");
		ep.addParam("campaign_id", campaignId);
		ep.addParam("template_id", templateId);
		return conn.get(ep.toString());
	}
}
