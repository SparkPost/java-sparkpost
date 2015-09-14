package com.sparkpost.sdk;

import com.sparkpost.sdk.dto.RecipientList;
import com.sparkpost.sdk.dto.Response;

/**
 * Resource collection that is a 1-to-1 match to the Recipient Lists SparkPost
 * API.<br>
 * <br>
 * See
 * <a href="https://www.sparkpost.com/api#/reference/recipient-lists/">Recipient
 * Lists API</a>
 * 
 * @author grava
 */
public class ResourceRecipientLists {

	static public Response create(RestConnection conn, Integer maxNumberOfRecipientErrors, RecipientList recipientList)
			throws SparkpostSdkException {
		String json = recipientList.toJson();
		Endpoint ep = new Endpoint("recipient-lists");
		ep.addParam("num_rcpt_errors", maxNumberOfRecipientErrors);
		return conn.post(ep.toString(), json);
	}

	static public Response retrieve(RestConnection conn, String recipientListId, Boolean showRecipients)
			throws SparkpostSdkException {
		Endpoint ep = new Endpoint("recipient-lists/" + recipientListId);
		ep.addParam("show_recipients", showRecipients);
		return conn.get(ep.toString());
	}

	static public Response listAll(RestConnection conn) throws SparkpostSdkException {
		return conn.get("recipient-lists");
	}

	static public Response delete(RestConnection conn, String recipientListId) throws SparkpostSdkException {
		String path = "recipient-lists/" + recipientListId;
		return conn.delete(path);
	}
}
