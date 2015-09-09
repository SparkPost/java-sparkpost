/* Copyright 2014 Message Systems, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this software except in compliance with the License.
 *
 * A copy of the License is located at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * or in the "license" file accompanying this software. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.sparkpost.sdk;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sparkpost.sdk.dto.Address;
import com.sparkpost.sdk.dto.Recipient;
import com.sparkpost.sdk.dto.StoredTemplate;
import com.sparkpost.sdk.dto.Template;
import com.sparkpost.sdk.dto.TemplateContent;
import com.sparkpost.sdk.dto.TransmissionWithRecipientArray;

/** This sample application creates a template, stores it at the server,
 *  and then creates a transmission using the stored template.
 *
 * @author grava
 */
public class SampleApplication {

    private static Gson gson;

    public static void main(String[] args) throws SparkpostSdkException {
        // Set to DEBUG so we can see what the SparkPost SDK is doing:
        Logger.getRootLogger().setLevel(Level.DEBUG);

        // Initialize our JSON parser. We'll use it to parse responses from
        // the SparkPost server.
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gson = gsonBuilder.setPrettyPrinting().create();

        System.out.println("*** SparkPost API Sample application ***");

        // ---------------------------------------------------
        // Create a client object:
        // ---------------------------------------------------
        Client client = new Client(System.getenv("SPARKPOST_API_KEY"));
        if (client.GetAuthKey() == null || client.GetAuthKey().isEmpty()) {
            System.err.println("API_KEY must be defined as an environment variable.");
            return;
        }

        client.setFromEmail(System.getenv("SPARKPOST_SENDER_EMAIL"));
        if (client.getFromEmail() == null || client.getFromEmail().isEmpty()) {
            System.err.println("SPARKPOST_SENDER_EMAIL must be defined as an environment variable.");
            return;
        }

        // ---------------------------------------------------
        // Create a connection object:
        // ---------------------------------------------------
        RestConn conn = new RestConn(client);

        // ---------------------------------------------------
        // Create a template and store it at the server:
        // ---------------------------------------------------
        String templateId = createTemplate(client, conn);
        if (templateId == null) {
            return;
        }

        // ---------------------------------------------------
        // Create a transmission using the stored template:
        // ---------------------------------------------------
        createTransmission(client, conn, templateId);

    }

    // Data Transfer Object for parsing a
    // 200 Response to a Template Create Request.
    // For use by gson.fromJson() in createTemplate()
    private class SPDTOResponse200_TemplateCreate {

        class _Results {

            String id;
        }
        _Results results;
    }

    // Create a template and store it at the server:
    private static String createTemplate(Client client, RestConn conn)
            throws SparkpostSdkException {
        Response response;
        Template tpl = new Template();
        tpl.name = "_TMP_TEMPLATE_TEST";
        tpl.content = new TemplateContent();
        tpl.content.from = new Address(client.getFromEmail(), "Testing", null);
        tpl.content.html = "Hello!";
        tpl.content.subject = "Template Test";
        response = ResourceTemplates.create(conn, tpl);

        if (response.getResponseCode() != 200) {
            System.err.println("Could not create template.");
            return null;
        }

        String json = response.getResponseBody();
        SPDTOResponse200_TemplateCreate dto;
        dto = gson.fromJson(json, SPDTOResponse200_TemplateCreate.class);
        System.out.println("Server says template was created with ID: " + dto.results.id);

        return dto.results.id;
    }

    // Data Transfer Object for parsing a
    // 200 Response to a Transmission Create Request.
    // For use by gson.fromJson() in createTransmission()
    private class SPDTOResponse200_TransmissionCreate {

        class _Results {

            int total_rejected_recipients;
            int total_accepted_recipients;
            String id;
        }
        _Results results;
    }
    
    // Create a transmission using the stored template:
    private static String createTransmission(
            Client client, RestConn conn, String templateId)
            throws SparkpostSdkException {
        TransmissionWithRecipientArray trans = new TransmissionWithRecipientArray();
        trans.campaign_id = "sample_app_trans_test";
        trans.return_path = client.getFromEmail();
        trans.recipientArray = new Recipient[1];
        trans.recipientArray[0] = new Recipient();
        trans.recipientArray[0].return_path = client.getFromEmail();
        trans.recipientArray[0].address
                = new Address("grava@messagesystems.com", "Guillaume Rava", null);
        trans.storedTemplate = new StoredTemplate();
        trans.storedTemplate.template_id = templateId;
        trans.storedTemplate.use_draft_template = true;
        Response response = ResourceTransmissions.create(conn, null, trans);
        if (response.getResponseCode() != 200) {
            System.err.println("Could not create transmission.");
            return null;
        }
        String json = response.getResponseBody();
        SPDTOResponse200_TransmissionCreate dto;
        dto = gson.fromJson(json, SPDTOResponse200_TransmissionCreate.class);
        System.out.println("Server says transmission was created with ID: " 
                + dto.results.id + 
                "\nTotal rejected recipients: " + 
                Integer.toString(dto.results.total_rejected_recipients) +
                "\nTotal accepted recipients: " + 
                Integer.toString(dto.results.total_accepted_recipients ));
        return dto.results.id;

    }
}
