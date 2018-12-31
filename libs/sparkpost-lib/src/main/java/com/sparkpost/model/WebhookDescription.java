
package com.sparkpost.model;

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
public class WebhookDescription extends Base {

    @Description(value = "User-friendly name", sample = {"Inbound Customer Replies"})
    private String name;

    @Description(value = "URL of the target to which to POST relay batches", sample = {"https://webhooks.customer.example/replies"})
    private String target;

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
    
    @Description(value = "Describes status of the webhook")
    private Boolean active;

}
