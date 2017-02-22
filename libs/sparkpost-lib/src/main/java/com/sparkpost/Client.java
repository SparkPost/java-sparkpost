
package com.sparkpost;

import java.util.ArrayList;
import java.util.List;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.OptionsAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.transport.RestConnection;

/**
 * The Client class stores everything specific to the SparkPost client:<BR>
 * <ul>
 * <li>The Authorization Key
 * <li>The "From:" email address
 * </ul>
 *
 * @author grava
 */
public class Client {

    private String authKey;

    private String username;

    private String password;

    private String fromEmail;

    public Client() {
    }

    public Client(String key) {
        setAuthKey(key);
    }

    /**
     * You can create and API Key here <a href="https://app.sparkpost.com/account/credentials">SparkPost</a>
     *
     * @param key
     *            SparkPost API Key
     */
    public final void setAuthKey(String key) {
        this.authKey = key;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fromEmail
     */
    public String getFromEmail() {
        return this.fromEmail;
    }

    /**
     * @param fromEmail
     *            the fromEmail to set
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public Response sendMessage(String from, String recipient, String subject, String text, String html) throws SparkPostException {
        List<String> recipients = new ArrayList<>();
        recipients.add(recipient);
        return sendMessage(from, recipients, subject, text, html);
    }

    public Response sendMessage(String from, List<String> recipients, String subject, String text, String html) throws SparkPostException {
        TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

        List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
        for (String recpient : recipients) {

            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recpient));
            recipientArray.add(recipientAttribs);
        }
        transmission.setRecipientArray(recipientArray);

        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();

        contentAttributes.setFrom(new AddressAttributes(from));

        contentAttributes.setSubject(subject);
        contentAttributes.setHtml(html);
        contentAttributes.setText(text);
        transmission.setContentAttributes(contentAttributes);

        if (from.toLowerCase().contains("@sparkpostbox.com")) {
            OptionsAttributes options = new OptionsAttributes();
            options.setSandbox(true);
            transmission.setOptions(options);
        }

        RestConnection connection = new RestConnection(this);
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        return response;
    }

    @Override
    public String toString() {
        return "client[email: " + this.fromEmail;
    }

}
