
package com.sparkpost.model;

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing info about a webhook.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Webhook extends Base {

    @Description(value = "Webhook id")
    private String id;

    @Description(value = "User-friendly name", sample = {"Inbound Customer Replies"})
    private String name;

    @Description(value = "URL of the target to which to POST relay batches", sample = {"https://webhooks.customer.example/replies"})
    private String target;

    @Description(value = "Type of authentication to be used during POST requests to target.", sample = {"none", "basic", "oauth2"})
    @SerializedName("auth_type")
    private String authType;

    @Description(value = "When using auth_type == 'oauth2', auth_request_details must be set by the user. "
            + "Additionally, auth_credentials is set by the system and cannot be configured by the user")
    @SerializedName("auth_request_details")
    private AuthRequestDetails authRequestDetails;

    @Description(value = "When using auth_type == 'basic', auth_credentials must be set by the user")
    @SerializedName("auth_credentials")
    private AuthCredentials authCredentials;

    @Deprecated
    @Description(
            value = "Authentication token to present in the X-MessageSystems-Webhook-Token header of POST requests to target",
            sample = {"5ebe2294ecd0e0f08eab7690d2a6ee69"})
    @SerializedName("auth_token")
    private String authToken;

    @Description(value = "Array of events", sample = {""})
    private List<String> events;

    @Description(
            value = "Restrict which inbound messages will be relayed to the target",
            sample = {"\"match\": { \"protocol\": \"SMTP\", \"domain\": \"replies.customer.example\" }"})
    private Match match;

}
