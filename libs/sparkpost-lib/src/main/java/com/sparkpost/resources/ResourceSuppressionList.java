package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SuppressionList;
import com.sparkpost.model.SuppressionListEntry;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.RestConnection;

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

	public static Response insertOrUpdate(RestConnection conn, String email, final SuppressionListEntry entry)
			throws SparkPostException {

		// we need a copy because we don't want the email field in the json:
		SuppressionListEntry copy = new SuppressionListEntry(entry);
		copy.setEmail(null);

		String json = copy.toJson();
		return conn.put("suppression-list/" + email, json);
	}

	public static Response insertOrUpdateBulk(RestConnection conn, SuppressionList suppressionList)
			throws SparkPostException {

		String json = suppressionList.toJson();
		return conn.put("suppression-list/", json);
	}

	public static Response search(RestConnection conn, String to, String from, String types, String limit)
			throws SparkPostException {

		Endpoint ep = new Endpoint("suppression-list");
		ep.addParam("to", to);
		ep.addParam("from", from);
		ep.addParam("types", types);
		ep.addParam("limit", limit);
		return conn.get(ep.toString());
	}

	public static Response check(RestConnection conn, String email) throws SparkPostException {

		return conn.get("suppression-list/" + email);
	}

	public static Response remove(RestConnection conn, String email) throws SparkPostException {

		return conn.delete("suppression-list/" + email);
	}
}
