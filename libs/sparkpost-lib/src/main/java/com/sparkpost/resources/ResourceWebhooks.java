
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.Webhook;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.WebhookCreateResponse;
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

    public static WebhookListAllResponse listAll(RestConnection conn, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks");
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep.toString());
        WebhookListAllResponse allWebhooks = WebhookListAllResponse.decode(response, WebhookListAllResponse.class);
        return allWebhooks;
    }

    public static WebhookCreateResponse create(RestConnection conn, Webhook webhook) throws SparkPostException {

        String json = webhook.toJson();
        Response response = conn.post("webhooks", json);
        WebhookCreateResponse webhookCreateResponse = WebhookCreateResponse.decode(response, WebhookCreateResponse.class);
        return webhookCreateResponse;
    }

    public static Response describe(RestConnection conn, String id, String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("webhooks/" + id);
        ep.addParam("timezone", timezone);
        Response response = conn.get(ep.toString());
        return response;
    }

    public static Response update(RestConnection conn, String id, Webhook webhook) throws SparkPostException {

        String json = webhook.toJson();
        Response response = conn.post("webhooks/" + id, json);
        return response;
    }

    public static Response delete(RestConnection conn, String id) throws SparkPostException {

        Response response = conn.delete("webhooks/" + id);
        return response;
    }
}
