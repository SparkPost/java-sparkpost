package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.transport.RestConnection;

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

	public static Response create(RestConnection conn, TemplateAttributes tpl) throws SparkPostException {

		String json = tpl.toJson();
		return conn.post("templates", json);
	}

	public static Response retrieve(RestConnection conn, String id, Boolean draft) throws SparkPostException {

		Endpoint ep = new Endpoint("templates/" + id);
		ep.addParam("draft", draft);
		return conn.get(ep.toString());
	}

	public static TemplateListResponse listAll(RestConnection conn) throws SparkPostException {
		Response response = conn.get("templates/");
		TemplateListResponse listResponse = (TemplateListResponse)TemplateListResponse.decode(response, TemplateListResponse.class);
		return listResponse;
	}

	public static Response update(RestConnection conn, String id, Boolean updatePublished, TemplateAttributes tpl)
			throws SparkPostException {

		Endpoint ep = new Endpoint("templates/" + id);
		ep.addParam("update_published", updatePublished);
		String json = tpl.toJson();
		return conn.put(ep.toString(), json);
	}

	public static Response preview(RestConnection conn, String id, Boolean draft, TemplateSubstitutionData subst)
			throws SparkPostException {

		Endpoint ep = new Endpoint("templates/" + id + "/preview");
		ep.addParam("draft", draft);
		String json = subst.toJson();
		return conn.put(ep.toString(), json);
	}

	public static Response delete(RestConnection conn, String id) throws SparkPostException {

		return conn.delete("templates/" + id);
	}
}
