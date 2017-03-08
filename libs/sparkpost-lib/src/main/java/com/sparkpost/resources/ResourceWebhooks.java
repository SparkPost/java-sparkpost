
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.Webhook;
import com.sparkpost.model.WebhookDescription;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.WebhookDescribeResponse;
import com.sparkpost.model.responses.WebhookIdContainerResponse;
import com.sparkpost.model.responses.WebhookListAllResponse;
import com.sparkpost.transport.IRestConnection;

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

    public static Response listSampleValuesAndEvents(IRestConnection conn) throws SparkPostException {
        Endpoint ep = new Endpoint("webhooks/events/documentation");
        Response response = conn.get(ep);
        return response;
    }

    public static Response getSamplePayloadForEvents(IRestConnection conn, String events) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/events/samples");
        ep.addParam("events", events);
        Response response = conn.get(ep);
        return response;
    }

    public static WebhookListAllResponse listAll(IRestConnection conn) throws SparkPostException {
        return ResourceWebhooks.listAll(conn, DEFAULT_TIMEZONE);
    }

    public static WebhookListAllResponse listAll(IRestConnection conn, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks");
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep);
        WebhookListAllResponse allWebhooks = WebhookListAllResponse.decode(response, WebhookListAllResponse.class);
        return allWebhooks;
    }

    public static WebhookIdContainerResponse create(IRestConnection conn, Webhook webhook) throws SparkPostException {

        String json = webhook.toJson();
        Endpoint ep = new Endpoint("webhooks");
        Response response = conn.post(ep, json);
        WebhookIdContainerResponse webhookIdContainerResponse = WebhookIdContainerResponse.decode(response, WebhookIdContainerResponse.class);
        return webhookIdContainerResponse;
    }

    public static WebhookDescribeResponse describe(IRestConnection conn, String id) throws SparkPostException {
        return ResourceWebhooks.describe(conn, id, DEFAULT_TIMEZONE);
    }

    public static WebhookDescribeResponse describe(IRestConnection conn, String id, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/" + id);
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep);
        WebhookDescribeResponse webhookDescribeResponse = WebhookDescribeResponse.decode(response, WebhookDescribeResponse.class);
        return webhookDescribeResponse;
    }

    public static WebhookIdContainerResponse update(IRestConnection conn, String id, WebhookDescription webhookDescription) throws SparkPostException {

        String json = webhookDescription.toJson(WebhookDescription.class);
        Endpoint ep = new Endpoint("webhooks/" + id);
        Response response = conn.put(ep, json);
        WebhookIdContainerResponse webhookIdContainerResponse = WebhookIdContainerResponse.decode(response, WebhookIdContainerResponse.class);
        return webhookIdContainerResponse;
    }

    public static Response delete(IRestConnection conn, String id) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/" + id);
        Response response = conn.delete(ep);
        return response;
    }
}
