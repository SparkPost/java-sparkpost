
package com.sparkpost.model.responses;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionCreateItem extends Response {

    @Description(value = "Number of recipients that were rejected", sample = {""})
    @SerializedName("total_rejected_recipients")
    private int totalRejectedRecipients;

    @Description(value = "Number of recipients accepted", sample = {""})
    @SerializedName("total_accepted_recipients")
    private int totalAcceptedRecipients;

    @Description(value = "Unique ID of transmission", sample = {""})
    private String id;
}
