package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthRequestDetails extends Base {

    @Description(value = "This is the URL SparkPost will request tokens from.", sample = {"https://oauth.myurl.com/tokens"})
    private String url;

    @Description(value = "OAuth 2.0 parameters that your target URL uses")
    private AuthRequestClientDetails body;


    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class AuthRequestClientDetails extends Base {

        @Description(value = "OAuth 2.0 Client ID")
        @SerializedName("client_id")
        private String clientId;

        @Description(value = "OAuth 2.0 Client Secret")
        @SerializedName("client_secret")
        private String clientSecret;
    }
}
