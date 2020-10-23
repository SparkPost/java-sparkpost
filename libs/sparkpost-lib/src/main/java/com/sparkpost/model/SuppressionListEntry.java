
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing an entry in a suppression list.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuppressionListEntry extends Base {

    public static final class StatusTypes {

        public static final String FBL = "FBL";
        public static final String SPAM_COMPLAING = "Spam Complaint";
        public static final String LIST_UNSUBSCRIBE = "List Unsubscribe";
        public static final String BOUNCE_RULE = "Bounce Rule";
        public static final String UNSUBSCRIBE_LINK = "Unsubscribe Link";
        public static final String MANUALLY_ADDED = "Manually Added";
        public static final String COMPLIANCE = "Compliance";
    }

    public static final class TypeTypes {

        public static final String TRANSACTIONAL_TYPE = "transactional";
        public static final String NON_TRANSACTIONAL_TYPE = "non_transactional";
    }

    public SuppressionListEntry() {

    }

    public SuppressionListEntry(SuppressionListEntry entry) {
        this.email = entry.email;
        //this.transactional = entry.transactional;
        //this.nonTransactional = entry.nonTransactional;
        this.type = entry.type;
        this.source = entry.source;
        this.description = entry.description;

    }

    /**
     *
     */
    @Description(value = "Email Address", sample = {"address@example.com"})
    private String email;

    /**
     * Whether the recipient requested to not receive any transactional messages
     * At a minimum, transactional or non_transactional is required upon creation of the entry.
     *
     * @deprecated Use Type instead
     */
    @Deprecated
    @Description(
            value = "Whether the recipient requested to not receive any transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.",
            sample = {"true"})
    private boolean transactional;

    /**
     * Whether the recipient requested to not receive any non-transactional messages
     * At a minimum, transactional or non_transactional is required upon creation of the entry.
     *
     * @deprecated Use Type instead
     */
    @Description(
            value = "Whether the recipient requested to not receive any non-transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.",
            sample = {"false"})
    @SerializedName("non_transactional")
    @Deprecated
    private boolean nonTransactional;

    /**
     * Source responsible for inserting the list entry. Valid values include: Spam Complaint, List Unsubscribe, Bounce Rule, Unsubscribe Link, Manually Added,
     * Compliance
     * defaults to Manually Added on create
     * See StatusTypes
     */
    @Description(
            value = "Source responsible for inserting the list entry. Valid values include: FBL, List Unsubscribe, Bounce Rule, Unsubscribe Link, Manually Added, Compliance. Defaults to Manually Added on create",
            sample = {"Manually Added"})
    private String source = StatusTypes.MANUALLY_ADDED;

    /**
     * Short explanation of the suppression
     */
    @Description(value = "Short explanation of the suppression", sample = {"Unsubscribed using list unsubscribe header"})
    private String description;

    @Description(value = "Type of suppression record. See TypeTypes", sample = {"transactional or non_transactional"})
    private String type;

    @Description(value = "Email address to be suppressed", sample = {"recip@example.com"})
    private String recipient;

    @Description(value = "Date suppression was created", sample = {"2017-10-01T12:00:00+00:00"})
    private String created;

    @Description(value = "Last time the suppression was updated", sample = {"2017-10-01T12:00:00+00:00"})
    private String updated;

    @Description(value = "Which subaccount the recipient is suppressed for. Only returned if suppressed for a specific subaccount.", sample = {"0"})
    @SerializedName("subaccount_id")
    private int subaccountId;

}
