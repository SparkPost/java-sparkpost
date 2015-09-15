package com.sparkpost.sdk.resources;

import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.SendingDomain;
import com.sparkpost.sdk.dto.VerifyAttributes;
import com.sparkpost.sdk.exception.SparkpostSdkException;
import com.sparkpost.sdk.transport.RestConnection;

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
