
package com.sparkpost.resources;

import java.net.URI;
import java.net.URISyntaxException;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.MessageEventsQueryBuilder;
import com.sparkpost.model.responses.MessageEventsResponse;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.IRestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Metrics SparkPost API.<br>
 * <br>
 * See <a href="https://developers.sparkpost.com/api/message-events.html/">Message Events
 * API</a>
 */
public class ResourceMessageEvents {

    public static MessageEventsResponse searchMessageEvents(IRestConnection conn) throws SparkPostException {
        return ResourceMessageEvents.searchMessageEvents(conn, 0, null);
    }

    public static MessageEventsResponse searchMessageEvents(IRestConnection conn, int perPage) throws SparkPostException {
        return ResourceMessageEvents.searchMessageEvents(conn, perPage, null);
    }

    public static MessageEventsResponse searchMessageEvents(IRestConnection conn, MessageEventsQueryBuilder queryBuilder) throws SparkPostException {
        return ResourceMessageEvents.searchMessageEvents(conn, 0, queryBuilder);
    }

    public static MessageEventsResponse searchMessageEvents(IRestConnection conn, int perPage, MessageEventsQueryBuilder queryBuilder)
            throws SparkPostException {
        Endpoint ep = new Endpoint("events/message");
        if (queryBuilder != null) {
            queryBuilder.buildQuery(ep);
        }

        if (perPage > 0) {
            ep.addParam("per_page", perPage);
        }
        Response response = conn.get(ep);

        MessageEventsResponse messageResponse = MessageEventsResponse.decode(response, MessageEventsResponse.class);
        return messageResponse;
    }

    public static MessageEventsResponse nextMessageEvents(IRestConnection conn, MessageEventsResponse page) throws SparkPostException {
        if (page.hasNext() == false) {
            return null;
        }

        String next = page.nextPageUrl();
        try {
            URI uri = new URI(next);

            String path = uri.getPath();
            path = path.replaceFirst("/api/v1/", "");
            Endpoint ep = new Endpoint(path);

            String query = uri.getQuery();
            if (query.length() > 0) {
                String[] split = query.split("&");
                for (String element : split) {
                    String[] fields = element.split("=", 2);
                    ep.addParam(fields[0], fields[1]);
                }
            }

            Response response = conn.get(ep);

            MessageEventsResponse messageResponse = MessageEventsResponse.decode(response, MessageEventsResponse.class);
            return messageResponse;
        } catch (URISyntaxException e) {
            throw new SparkPostException(e);
        }
    }

}
