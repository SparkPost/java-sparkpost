
package com.sparkpost;

import java.util.List;
import java.util.Map;

import com.sparkpost.model.AddressAttributes;

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
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fromEmail
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * @param fromEmail
     *            the fromEmail to set
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public void sendMessage(String termplateId, List<AddressAttributes> recipients, Map<String, String> args) {

    }

    @Override
    public String toString() {
        return "client[email: " + this.fromEmail;
    }

}
