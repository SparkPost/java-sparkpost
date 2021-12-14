
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SubaccountInfo;
import com.sparkpost.model.responses.SubaccountCreateResponse;
import com.sparkpost.resources.ResourceSubAccountCrud;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class SubaccountCRUDSample extends SparkPostBaseApp {

    private static final Logger logger = LogManager.getLogger(SubaccountCRUDSample.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SubaccountCRUDSample sample = new SubaccountCRUDSample();
        sample.runApp();

    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        Integer subaccountId = creatSubAccount();

    }

    /**
     * Demonstrates how create subaccounts in SparkPost
     *
     * @throws SparkPostException
     */
    public Integer creatSubAccount() throws SparkPostException {
        if (logger.isDebugEnabled()) {
            logger.debug("creatSubAccount()");
        }
        SubaccountInfo subaccountInfo = new SubaccountInfo();

        subaccountInfo.setName("Test");
        subaccountInfo.setSetupApiKey(true);
        subaccountInfo.addKeyGrant(SubaccountInfo.SMTP_INJECTION_GRANT);

        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        SubaccountCreateResponse response = ResourceSubAccountCrud.create(connection, subaccountInfo);

        if (logger.isDebugEnabled()) {
            logger.debug("Create Subaccount Response: " + response);
        }

        return response.getResults().getId();

    }

    public void getSubaccountInfo(Integer id) throws SparkPostException {
        if (logger.isDebugEnabled()) {
            logger.debug("getSubaccountInfo()");
        }

        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        SubaccountCreateResponse response = ResourceSubAccountCrud.get(connection, id);

        if (logger.isDebugEnabled()) {
            logger.debug("Create Subaccount Response: " + response);
        }

    }

    public void sendEmail(String templateName, List<String> recipients) {
        if (logger.isDebugEnabled()) {
            logger.debug("sendEmail(...)");
        }

    }

}
