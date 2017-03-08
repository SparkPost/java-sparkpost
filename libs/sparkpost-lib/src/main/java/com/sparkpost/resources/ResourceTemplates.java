
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateCreateResponse;
import com.sparkpost.model.responses.TemplateItemResponse;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.model.responses.TemplatePreviewResponse;
import com.sparkpost.model.responses.TemplateRetrieveResponse;
import com.sparkpost.transport.IRestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Templates SparkPost API.
 * <br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/templates/">Templates
 * API</a>
 */
public class ResourceTemplates {

    public static TemplateCreateResponse create(IRestConnection conn, TemplateAttributes tpl) throws SparkPostException {

        String json = tpl.toJson();
        Endpoint ep = new Endpoint("templates");
        Response response = conn.post(ep, json);
        TemplateCreateResponse createResponse = TemplateCreateResponse.decode(response, TemplateCreateResponse.class);
        return createResponse;
    }

    public static TemplateRetrieveResponse retrieve(IRestConnection conn, String id, Boolean draft) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("draft", draft);
        Response response = conn.get(ep);

        TemplateRetrieveResponse templateResponse = (TemplateRetrieveResponse) TemplateItemResponse.decode(response, TemplateRetrieveResponse.class);
        return templateResponse;
    }

    public static TemplateListResponse listAll(IRestConnection conn) throws SparkPostException {
        Endpoint ep = new Endpoint("templates/");
        Response response = conn.get(ep);
        TemplateListResponse listResponse = (TemplateListResponse) TemplateListResponse.decode(response, TemplateListResponse.class);
        return listResponse;
    }

    public static Response update(IRestConnection conn, String id, Boolean updatePublished, TemplateAttributes tpl) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("update_published", updatePublished);
        String json = tpl.toJson();
        Response response = conn.put(ep, json);
        return response;
    }

    public static TemplatePreviewResponse preview(IRestConnection conn, String id, Boolean draft, TemplateSubstitutionData subst) throws SparkPostException {

        Endpoint ep = new Endpoint("templates/" + id + "/preview");
        ep.addParam("draft", draft);
        String json = subst.toJson();
        Response response = conn.post(ep, json);
        TemplatePreviewResponse newResponse = TemplatePreviewResponse.decode(response, TemplatePreviewResponse.class);
        return newResponse;
    }

    public static Response delete(IRestConnection conn, String id) throws SparkPostException {
        Endpoint ep = new Endpoint("templates/" + id);
        Response response = conn.delete(ep);
        // Delete response is an empty dictionary so no need to deserialize the JSON object.
        return response;
    }
}
