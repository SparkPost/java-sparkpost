
package com.sparkpost;

import java.net.InetSocketAddress;
import java.net.Proxy;
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
import com.sparkpost.transport.IRestConnection;
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

    private Proxy proxy;

    private boolean disconnectAfterRequest = false;

    private int httpConnectTimeout = 0; // 0 - system default

    private int httpReadTimeout = 0; // 0 - system default

    private String baseUrl = IRestConnection.SPC_US_ENDPOINT;

    public Client() {

    }

    public Client(String key) {
        setAuthKey(key);
    }

    public Client(String key, String baseUrl) {
        setAuthKey(key);

        this.baseUrl = baseUrl;
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

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
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
     * If true will be more aggressive about disconnecting idle HTTP connections
     *
     * @return true
     */
    public boolean isDisconnectAfterRequest() {
        return this.disconnectAfterRequest;
    }

    /**
     * If true the underlying HTTP transport will be more aggressive about closing idle HTTP connection so may not resuse TCP sockets as much.
     *
     * @param disconnectAfterRequest
     *            default is false
     */
    public void setDisconnectAfterRequest(boolean disconnectAfterRequest) {
        this.disconnectAfterRequest = disconnectAfterRequest;
    }

    public int getHttpConnectTimeout() {
        return this.httpConnectTimeout;
    }

    public void setProxy(String hostname, int port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    /**
     * Sets a specified timeout value, in milliseconds, to be used
     * when opening a communications link to the resource referenced
     * by this URLConnection. If the timeout expires before the
     * connection can be established, a
     * java.net.SocketTimeoutException is raised. A timeout of zero is
     * interpreted as an infinite timeout.
     **/
    public void setHttpConnectTimeout(int timeout) {
        this.httpConnectTimeout = timeout;
    }

    public int getHttpReadTimeout() {
        return this.httpReadTimeout;
    }

    public void setHttpReadTimeout(int httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
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
        for (String recipient : recipients) {

            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recipient));
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

        IRestConnection connection = new RestConnection(this, this.baseUrl);
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        return response;
    }

    @Override
    public String toString() {
        return "client[email: " + this.fromEmail;
    }

}
