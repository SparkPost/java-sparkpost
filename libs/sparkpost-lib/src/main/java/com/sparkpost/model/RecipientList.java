
package com.sparkpost.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A recipient list is a collection of recipients that can be used in a
 * transmission. The Recipient List API provides the means to manage recipient
 * lists. When creating a new transmission using the Transmissions API, the
 * recipients may be submitted "inline" as part of the transmission data, or a
 * stored recipient list id attribute can be specified.The Recipient List API
 * operates on lists as a whole and does not currently support management of
 * individual recipients.Recipient List Attributes
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientList extends Base {

    /**
     * Short, unique, recipient list identifier
     * If an id is not specified, one is generated. Maximum length - 64 bytes
     */
    @Description(value = "Short, unique, recipient list identifier. Maximum length - 64 bytes", sample = {""})
    private String id = null;

    /**
     * Short, pretty/readable recipient list display name, not required to be unique
     * If a name is not specified, then defaults to the same value as id. Maximum length - 64 bytes
     */
    @Description(value = " Short, pretty/readable recipient list display name, not required to be unique. Maximum length - 64 bytes", sample = {""})
    private String name = null;

    /**
     * Detailed description of the recipient list
     * Maximum length - 1024 bytes
     */
    @Description(value = "Detailed description of the recipient list. Maximum length - 1024 bytes", sample = {""})
    private String description = null;

    /**
     * Recipient list attribute object
     * This JSON object allows users to store arbitrary metadata related to this list. This data is not used by the API. It is only for the user.
     */
    @Description(value = " Recipient list attribute object", sample = {""})
    private Map<String, String> attributes = null;

    /**
     * List of recipient objects
     * For a full description, see the Recipient Attributes.
     */
    @Description(value = " List of recipient objects", sample = {""})
    private List<RecipientAttributes> recipients = null;

    @Description(value = "The number of recipients in this list that have been accepted", sample = {""})
    @SerializedName("total_accepted_recipients")
    private int totalAcceptedRecipients;

    @Description(value = "The number of recipients in this list that have been rejected", sample = {""})
    @SerializedName("total_rejected_recipients")
    private int totalRejectedRecipients;

    /**
     * Returns object that can be used to create transmission.
     *
     * @return StoredRecipientList object
     */
    public StoredRecipientList asStoredRecipientList() {

        StoredRecipientList storedRecipientList = new StoredRecipientList();
        storedRecipientList.setListId(this.getId());
        return storedRecipientList;
    }
}
