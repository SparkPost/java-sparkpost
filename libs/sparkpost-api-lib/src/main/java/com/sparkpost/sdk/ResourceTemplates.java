package com.sparkpost.sdk;

import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.TemplateAttributes;
import com.sparkpost.sdk.dto.TemplateSubstitutionData;

/**
 * Resource collection that is a 1-to-1 match to the Templates SparkPost API.
 * <br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/templates/">Templates
 * API</a>
 *
 * @author grava
 */
public class ResourceTemplates {

	public static Response create(RestConnection conn, TemplateAttributes tpl) throws SparkpostSdkException {

		String json = tpl.toJson();
		return conn.post("templates", json);
	}

	public static Response retrieve(RestConnection conn, String id, Boolean draft) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("templates/" + id);
		ep.addParam("draft", draft);
		return conn.get(ep.toString());
	}

	public static Response listAll(RestConnection conn) throws SparkpostSdkException {

		return conn.get("templates/");
	}

	public static Response update(RestConnection conn, String id, Boolean updatePublished, TemplateAttributes tpl)
			throws SparkpostSdkException {

		Endpoint ep = new Endpoint("templates/" + id);
		ep.addParam("update_published", updatePublished);
		String json = tpl.toJson();
		return conn.put(ep.toString(), json);
	}

	public static Response preview(RestConnection conn, String id, Boolean draft, TemplateSubstitutionData subst)
			throws SparkpostSdkException {

		Endpoint ep = new Endpoint("templates/" + id + "/preview");
		ep.addParam("draft", draft);
		String json = subst.toJson();
		return conn.put(ep.toString(), json);
	}

	public static Response delete(RestConnection conn, String id) throws SparkpostSdkException {

		return conn.delete("templates/" + id);
	}
}
