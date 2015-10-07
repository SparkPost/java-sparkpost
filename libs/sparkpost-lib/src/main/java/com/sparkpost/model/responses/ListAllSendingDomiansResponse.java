
package com.sparkpost.model.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListAllSendingDomiansResponse extends Response {

    @Data
    @EqualsAndHashCode
    public static final class Domain {

        @Description(value = "Name of the sending domain", sample = {"example.com"})
        String domain;

        @Description(value = "Associated tracking domain", sample = {"click.example.com"})
        @SerializedName("tracking_domain")
        String trackingDomain;
    }

    @Description(value = "List of Sending Domains", sample = {"Array of sending domains"})
    @SerializedName("results")
    List<Domain> domains;
}
