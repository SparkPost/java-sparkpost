
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateCreateResponse;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.model.responses.TemplatePreviewResponse;
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

    public static TemplateCreateResponse create(RestConnection conn, TemplateAttributes tpl) throws SparkPostException {

        String json = tpl.toJson();
        Response response = conn.post("templates", json);
        TemplateCreateResponse createResponse = TemplateCreateResponse.decode(response, TemplateCreateResponse.class);
        return createResponse;
    }

    public static Response retrieve(RestConnection conn, String id, Boolean draft) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("draft", draft);
        Response response = conn.get(ep.toString());
        return response;
    }

    public static TemplateListResponse listAll(RestConnection conn) throws SparkPostException {
        Response response = conn.get("templates/");
        TemplateListResponse listResponse = (TemplateListResponse) TemplateListResponse.decode(response, TemplateListResponse.class);
        return listResponse;
    }

    public static Response update(RestConnection conn, String id, Boolean updatePublished, TemplateAttributes tpl) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("update_published", updatePublished);
        String json = tpl.toJson();
        Response response = conn.put(ep.toString(), json);
        return response;
    }

    public static TemplatePreviewResponse preview(RestConnection conn, String id, Boolean draft, TemplateSubstitutionData subst) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id + "/preview");
        ep.addParam("draft", draft);
        String json = subst.toJson();
        Response response = conn.post(ep.toString(), json);
        TemplatePreviewResponse newResponse = TemplatePreviewResponse.decode(response, TemplatePreviewResponse.class);
        return newResponse;
    }

    public static Response delete(RestConnection conn, String id) throws SparkPostException {

        Response response = conn.delete("templates/" + id);
        // Delete response is an empty dictionary so no need to deserialize the JSON object.
        return response;
    }
}
