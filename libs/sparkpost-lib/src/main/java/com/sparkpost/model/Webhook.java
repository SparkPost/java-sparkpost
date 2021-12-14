package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Webhook extends WebhookDescription {

    @Description(value = "Webhook id")
    private String id;

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

}
