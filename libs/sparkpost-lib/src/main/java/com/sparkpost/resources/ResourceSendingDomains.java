package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SendingDomain;
import com.sparkpost.model.VerifyAttributes;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.RestConnection;

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

	public static Response create(RestConnection conn, SendingDomain domain) throws SparkPostException {

		String json = domain.toJson();
		Response response = conn.post("sending-domains", json);
		return response;
	}

	public static Response retrieve(RestConnection conn, String domainName) throws SparkPostException {

		Response response = conn.get("sending-domains/" + domainName);
		return response;
	}

	public static Response list(RestConnection conn) throws SparkPostException {

		Response response = conn.get("sending-domains/");
		return response;
	}

	public static Response update(RestConnection conn, String domainName, SendingDomain domain) throws SparkPostException {

		String json = domain.toJson();
		Response response = conn.put("sending-domains/" + domainName, json);
		return response;
	}

	public static Response verify(RestConnection conn, String domainName, VerifyAttributes verify)
			throws SparkPostException {

		String json = verify.toJson();
		Response response = conn.post("sending-domains/" + domainName + "/verify", json);
		return response;
	}
}
