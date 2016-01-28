
package com.sparkpost;

import com.sparkpost.exception.SparkPostException;

public class SparkPost {

    public static void main(String[] args) throws SparkPostException {
        String API_KEY = "YOUR API KEY HERE!!!";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");

    }
}
