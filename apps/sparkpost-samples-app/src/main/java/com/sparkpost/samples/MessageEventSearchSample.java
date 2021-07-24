
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostErrorServerResponseException;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.MessageEventsQueryBuilder;
import com.sparkpost.model.responses.MessageEventsResponse;
import com.sparkpost.resources.ResourceMessageEvents;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Note: This code has a lot of redundancy so it is easy to copy and paste into your own code base as a starting point.
 * Also, This code does not do any of the error checking that should be done in production code.
 */
public class MessageEventSearchSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    public static void main(String[] args) throws SparkPostException, IOException, InterruptedException {
        Configurator.setRootLevel(Level.DEBUG);

        MessageEventSearchSample app = new MessageEventSearchSample();
        app.doSearch();
    }

    private void doSearch() throws SparkPostException, IOException, InterruptedException {
        boolean dumpResult = false;
        Client client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(client, getEndPoint());

        MessageEventsResponse response = null;
        do {
            if (response == null) {

                // Message events can be filtered with the Message Event Query Builder
                MessageEventsQueryBuilder query = null;
                query = new MessageEventsQueryBuilder();

                // Query by date range
                //query.setFromDateTime("2020-09-27T00:00");
                //query.setToDateTime("2020-10-01T00:00");

                // Query by Message Id
                //query.addMessageId("Message ID Here");

                response = ResourceMessageEvents.searchMessageEvents(connection, 10, query);
            } else {
                try {
                    response = ResourceMessageEvents.nextMessageEvents(connection, response);
                } catch (SparkPostErrorServerResponseException e) {
                    if (e.getResponseCode() == 429) {
                        // We hit rate limit. Retry request again after a delay
                        System.out.println("Hit the rate limit!!! Continue after delay...");
                        Thread.sleep(30000);
                        continue;
                    }
                    throw e;
                }
            }

            if (response == null) {
                return;
            } else if (response.getResponseCode() != 200) {
                System.err.println(response);
                return;
            }

            if (dumpResult) {
                System.out.println("Response: " + response);

                List<Map<String, Object>> results = response.getResults();
                for (Map<String, Object> result : results) {
                    System.out.println("\nResult (" + result.get("type") + ")");
                    for (Map.Entry<String, Object> entry : result.entrySet()) {
                        System.out.println("\t" + entry.getKey() + "=" + entry.getValue());
                    }
                }
            }
            System.out.println("Total Count: " + response.getTotalCount());
            System.out.println("Links: " + response.getLinks());
            System.out.println("Has Next Page: " + response.hasNext());

        } while (response.hasNext());
    }

}
