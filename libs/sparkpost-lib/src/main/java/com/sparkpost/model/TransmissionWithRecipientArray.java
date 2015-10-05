
package com.sparkpost.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for a transmission using an array of recipients.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionWithRecipientArray extends TransmissionBase {

    /**
     * Inline recipient objects or object containing stored recipient list ID
     * Specify a stored recipient list or specify recipients inline. When using
     * a stored recipient list, specify the "list_id" as described in Using a
     * Stored Recipient List. Otherwise, provide the recipients inline using the
     * fields described in the Recipient List API documentation for Recipient
     * Attributes.
     */
    @Description(value = "Inline recipient objects or object containing stored recipient list ID", sample = {""})
    @SerializedName("recipients")
    private List<RecipientAttributes> recipientArray = null;

}
