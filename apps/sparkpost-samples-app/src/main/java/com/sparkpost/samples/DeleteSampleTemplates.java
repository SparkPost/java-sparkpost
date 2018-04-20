
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateItem;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Delete all test templates created by the sample code
 */
public class DeleteSampleTemplates extends SparkPostBaseApp {

    static final Logger logger = LoggerFactory.getLogger(DeleteSampleTemplates.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        DeleteSampleTemplates sample = new DeleteSampleTemplates();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(client, getEndPoint());

        // Get All Templates
        TemplateListResponse listResponse = ResourceTemplates.listAll(connection);
        List<TemplateItem> results = listResponse.getResults();

        for (TemplateItem item : results) {

            // Delete any template with the name  "_TMP_TEMPLATE_TEST"
            if (item.getName().equals(SAMPLE_TEMPLATE_NAME)) {
                deleteTemplate(connection, item.getId());
            }
        }
    }

    private void deleteTemplate(IRestConnection connection, String templateId) throws SparkPostException {
        Response deleteResponse = ResourceTemplates.delete(connection, templateId);
        if (deleteResponse.getResponseCode() == 200) {
            System.out.println("\tdeleted: " + templateId);
        } else {
            System.out.println("\tError: Failed to delete: " + templateId + ") because " + deleteResponse.getResponseMessage());
        }
    }
}
