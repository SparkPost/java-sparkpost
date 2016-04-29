
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.Webhook;
import com.sparkpost.model.WebhookDescription;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.WebhookDescribeResponse;
import com.sparkpost.model.responses.WebhookIdContainerResponse;
import com.sparkpost.model.responses.WebhookListAllResponse;
import com.sparkpost.transport.RestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Webhooks SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/webhooks/">Webhooks
 * API</a>
 *
 * @author grava
 */
public class ResourceWebhooks {

    private static final String DEFAULT_TIMEZONE = "UTC";

    public static Response listSampleValuesAndEvents(RestConnection conn) throws SparkPostException {
        Response response = conn.get("webhooks/events/documentation");
        return response;
    }

    public static Response getSamplePayloadForEvents(RestConnection conn, String events) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/events/samples");
        ep.addParam("events", events);
        Response response = conn.get(ep.toString());
        return response;
    }

    public static WebhookListAllResponse listAll(RestConnection conn) throws SparkPostException {
        return listAll(conn, DEFAULT_TIMEZONE);
    }

    public static WebhookListAllResponse listAll(RestConnection conn, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks");
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep.toString());
        WebhookListAllResponse allWebhooks = WebhookListAllResponse.decode(response, WebhookListAllResponse.class);
        return allWebhooks;
    }

    public static WebhookIdContainerResponse create(RestConnection conn, Webhook webhook) throws SparkPostException {

        String json = webhook.toJson();
        Response response = conn.post("webhooks", json);
        WebhookIdContainerResponse webhookIdContainerResponse = WebhookIdContainerResponse.decode(
                response,
                WebhookIdContainerResponse.class
        );
        return webhookIdContainerResponse;
    }

    public static WebhookDescribeResponse describe(RestConnection conn, String id) throws SparkPostException {
        return describe(conn, id, DEFAULT_TIMEZONE);
    }

    public static WebhookDescribeResponse describe(RestConnection conn, String id, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/" + id);
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep.toString());
        WebhookDescribeResponse webhookDescribeResponse = WebhookDescribeResponse.decode(response, WebhookDescribeResponse.class);
        return webhookDescribeResponse;
    }

    public static WebhookIdContainerResponse update(RestConnection conn, String id, WebhookDescription webhookDescription) throws SparkPostException {

        String json = webhookDescription.toJson(WebhookDescription.class);
        Response response = conn.put("webhooks/" + id, json);
        WebhookIdContainerResponse webhookIdContainerResponse = WebhookIdContainerResponse.decode(
                response,
                WebhookIdContainerResponse.class
        );
        return webhookIdContainerResponse;
    }

    public static Response delete(RestConnection conn, String id) throws SparkPostException {

        Response response = conn.delete("webhooks/" + id);
        return response;
    }
}
