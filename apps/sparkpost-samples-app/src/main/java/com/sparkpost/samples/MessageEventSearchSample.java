
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostErrorServerResponseException;
import com.sparkpost.exception.SparkPostException;
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

    static final Logger logger = Logger.getLogger(CreateTemplateSimple.class);

    public static void main(String[] args) throws SparkPostException, IOException, InterruptedException {
        Logger.getRootLogger().setLevel(Level.DEBUG);

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
                response = ResourceMessageEvents.searchMessageEvents(connection, 10);
            } else {
                try {
                    response = ResourceMessageEvents.nextMessageEvents(connection, response);
                } catch (SparkPostErrorServerResponseException e) {
                    if (e.getResponseCode() == 429) {
                        // We hit rate limit. Retry request again after a delay
                        System.out.println("Hit the rate limit!!! Will retry after delay...");
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
                    Set<String> keySet = result.keySet();
                    for (String key : keySet) {
                        // Print out result
                        System.out.println("\t" + key + " = " + result.get(key));
                    }
                }
            }
            System.out.println("Total Count: " + response.getTotalCount());
            System.out.println("Links: " + response.getLinks());
            System.out.println("Has Next Page: " + response.hasNext());

        } while (response != null && response.hasNext());
    }

}
