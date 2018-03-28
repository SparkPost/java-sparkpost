
package com.sparkpost;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.transport.IRestConnection;

public class SparkPost {

    public static void main(String[] args) throws SparkPostException {
        String API_KEY = "YOUR API KEY HERE!!!";

        // To use the SparkPost EU use IRestConnection.SPC_EU_ENDPOINT instead of the SPC_US_ENDPOINT
        Client client = new Client(API_KEY, IRestConnection.SPC_EU_ENDPOINT);

        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");

    }
}
