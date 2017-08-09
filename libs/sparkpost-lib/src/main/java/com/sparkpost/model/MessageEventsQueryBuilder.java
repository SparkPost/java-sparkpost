
package com.sparkpost.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.sparkpost.resources.Endpoint;

public class MessageEventsQueryBuilder {

    private boolean sortOutput = false;

    private Set<BounceClass> bounceClasses = new HashSet<BounceClass>();
    private Set<String> campaignIds = new HashSet<String>();
    private Set<EventType> events = new HashSet<EventType>();
    private Set<String> friendlyFroms = new HashSet<String>();
    private String fromDateTime;
    private String toDateTime;
    private Set<String> messageIds = new HashSet<String>();
    private String reason;
    private Set<String> recipients = new HashSet<String>();
    private Set<String> subaccounts = new HashSet<String>();
    private Set<String> templateIds = new HashSet<String>();
    private String timezone;
    private Set<String> transmissionIds = new HashSet<String>();

    /**
     * Bounce Classification Codes:
     * https://www.sparkpost.com/docs/deliverability/bounce-classification-codes
     */
    public enum BounceClass {
        UNDERTERMINED(1, "Undetermined", "The response text could not be identified.", "Undetermined"),
        INVALID_RECIPIENT(10, "Invalid Recipient", "The recipient is invalid.", "Hard"),
        SOFT_BOUNCE(20, "Soft Bounce", "The message soft bounced.", "Soft"),
        DNS_FAILURE(21, "DNS Failure", "The message bounced due to a DNS failure.", "Soft"),
        MAILBOX_FULL(22, "Mailbox Full", "The message bounced due to the remote mailbox being over quota.", "Soft"),
        TOO_LARGE(23, "Too Large", "The message bounced because it was too large for the recipient.", "Soft"),
        TIMEOUT(24, "Timeout", "The message timed out.", "Soft"),
        ADMIN_FAILURE(25, "Admin Failure", "The message was failed by SparkPost’s configured policies.", "Admin"),
        NO_RCPT(30, "Generic Bounce", "No RCPT No recipient could be determined for the message.", "Hard"),
        GENERIC_BOUCNE(40, "Generic Bounce", "The message failed for unspecified reasons.", "Soft"),
        MAIL_BLOCK(50, "Mail Block", "The message was blocked by the receiver.", "Block"),
        SPAM_BLOCK(51, "Spam Block", "The message was blocked by the receiver as coming from a known spam source.", "Block"),
        SPAM_CONTENT(52, "Spam Content", "The message was blocked by the receiver as spam.", "Block"),
        PROHIBITED_ATTACHMENT(53, "Prohibited Attachment", "The message was blocked by the receiver because it contained an attachment.", "Block"),
        RELAYING_DENIED(54, "Relaying Denied", "The message was blocked by the receiver because relaying is not allowed.", "Block"),
        AUTO_REPLY(60, "Auto-Reply", "The message is an auto-reply/vacation mail.", "Soft"),
        TRANSIENT_FAILURE(70, "Transient Failure", "Message transmission has been temporarily delayed.", "Soft"),
        SUBSCRIBE(80, "Subscribe", "The message is a subscribe request.", "Admin"),
        UNSUBSCRIBE(90, "Unsubscribe", "The message is an unsubscribe request.", "Hard"),
        CHALLENGE_RESPONSE(100, "Challenge-Response", "The message is a challenge-response probe.", "Soft");

        private final int classification;

        private final String bounceName;

        private final String description;

        private final String category;

        private BounceClass(int classification, String bounceName, String description, String category) {
            this.classification = classification;
            this.bounceName = bounceName;
            this.description = description;
            this.category = category;
        }

        public int classification() {
            return this.classification;
        }

        public String bounceName() {
            return this.bounceName;
        }

        public String description() {
            return this.description;
        }

        public String getCategory() {
            return this.category;
        }

        @Override
        public String toString() {
            return "" + this.classification;
        }

    }

    public enum EventType {
        DELIVERY("delivery"),
        INJECTION("injection"),
        BOUNCE_DELAY("bounce,delay"),
        POLICY_REJECTION("policy_rejection"),
        OUT_OF_BAND("out_of_band"),
        OPEN_CLICK("open,click"),
        GENERATION_FAILURE("generation_failure"),
        GENERATION_REJECTION("generation_rejection"),
        SPAM_COMPLAINT("spam_complaint"),
        LIST_UNSUBSCRIBE("list_unsubscribe"),
        LINK_UNSUBSCRIBE("link_unsubscribe");

        private final String eventType;

        private EventType(String eventType) {
            this.eventType = eventType;
        }

        public String eventType() {
            return this.eventType;
        }

        @Override
        public String toString() {
            return this.eventType;
        }

    }

    /**
     * bounce_classes - delimited list of bounce classification codes to search.
     * See Bounce Classification Codes: https://www.sparkpost.com/docs/deliverability/bounce-classification-codes
     **/
    public void addBounceClass(BounceClass bounceClass) {
        this.bounceClasses.add(bounceClass);
    }

    /**
     * campaign_ids - delimited list of campaign ID’s to search (i.e. the campaign id used during creation of a transmission).
     * Notes: Not available for sms_status type.
     **/
    public void addCampaignId(String cid) {
        this.campaignIds.add(cid);
    }

    /**
     * events - delimited list of event types to search. Defaults to all event types.
     * Example:
     * delivery,injection,bounce,delay,policy_rejection,out_of_band,open,click,generation_failure,generation_rejection,spam_complaint,list_unsubscribe,link_unsubscribe
     **/
    public void addEventType(EventType type) {
        this.events.add(type);
    }

    /**
     * friendly_froms - delimited list of friendly from emails to search.
     * Notes: Not available for sms_status type.
     * Example: sender@mail.example.com
     **/
    public void addFriendlyFrom(String from) {
        this.friendlyFroms.add(from);
    }

    /**
     * From Datetime in format of YYYY-MM-DDTHH:MM.
     * Default: 24 hours ago
     * Example: 2014-07-20T08:00
     **/
    public void setFromDateTime(String dateTime) {
        this.fromDateTime = dateTime;
    }

    public void setToDateTime(String dateTime) {
        this.toDateTime = dateTime;
    }

    /**
     * message_ids - delimited list of message ID’s to search.
     * Example: 0e0d94b7-9085-4e3c-ab30-e3f2cd9c273e
     **/
    public void addMessageId(String messageId) {
        this.messageIds.add(messageId);
    }

    /**
     * reason - Bounce/failure/rejection reason that will be matched using a wildcard (e.g., %reason%).
     * Example: bounce
     **/
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * recipients - delimited list of recipients to search.
     * Example: recipient@example.com
     **/
    public void addRecipient(String recipient) {
        this.recipients.add(recipient);
    }

    /**
     * subaccounts - delimited list of subaccount ID’s to search.
     * Example: 101
     **/
    public void addSubAccount(String subaccount) {
        this.subaccounts.add(subaccount);
    }

    /**
     * template_ids - delimited list of template ID’s to search.
     * Example: templ-1234
     **/
    public void addTemplateId(String tid) {
        this.templateIds.add(tid);
    }

    /**
     * timezone - Standard timezone identification string.
     * Default: UTC
     * Example: America/New_York
     **/
    public void setTimezone(String timeZone) {
        this.timezone = timeZone;
    }

    /**
     * transmission_ids - delimited list of transmission ID’s to search (i.e. id generated during creation of a transmission).
     * Example: 65832150921904138
     **/
    public void addTransmissionId(String tid) {
        this.transmissionIds.add(tid);
    }

    public void buildQuery(Endpoint endpoint) {
        if (this.bounceClasses.size() > 0) {
            endpoint.addParam("bounce_classes", setAsString(this.bounceClasses, ","));
        }

        if (this.campaignIds.size() > 0) {
            endpoint.addParam("campaign_ids", setAsString(this.campaignIds, ","));
        }

        if (this.events.size() > 0) {
            endpoint.addParam("events", setAsString(this.events, ","));
        }

        if (this.friendlyFroms.size() > 0) {
            endpoint.addParam("friendly_froms", setAsString(this.friendlyFroms, ","));
        }

        if (StringUtils.isNotEmpty(this.fromDateTime)) {
            endpoint.addParam("from", this.fromDateTime);
        }

        if (StringUtils.isNotEmpty(this.toDateTime)) {
            endpoint.addParam("to", this.toDateTime);
        }

        if (this.messageIds.size() > 0) {
            endpoint.addParam("message_ids", setAsString(this.messageIds, ","));
        }

        if (StringUtils.isNotEmpty(this.reason)) {
            endpoint.addParam("reason", this.reason);
        }

        if (this.recipients.size() > 0) {
            endpoint.addParam("recipients", setAsString(this.recipients, ","));
        }

        if (this.subaccounts.size() > 0) {
            endpoint.addParam("subaccounts", setAsString(this.subaccounts, ","));
        }

        if (this.templateIds.size() > 0) {
            endpoint.addParam("template_ids", setAsString(this.templateIds, ","));
        }

        if (StringUtils.isNotEmpty(this.timezone)) {
            endpoint.addParam("timezone", this.timezone);
        }

        if (this.transmissionIds.size() > 0) {
            endpoint.addParam("transmission_ids", setAsString(this.transmissionIds, ","));
        }
    }

    // To make test easier output can be sorted so value content is deterministic
    public void setSortOutput(boolean sortOutput) {
        this.sortOutput = sortOutput;
    }

    private String setAsString(@SuppressWarnings("rawtypes") Set set, String separator) {

        List<String> list = new ArrayList<String>();
        for (Object obj : set) {
            list.add(Objects.toString(obj));
        }

        if (this.sortOutput) {
            Collections.sort(list);
        }

        StringBuilder result = new StringBuilder();
        boolean isFirstElement = true;
        for (String val : list) {
            if (!isFirstElement) {
                result.append(separator);
            }
            result.append(val);
            isFirstElement = false;
        }

        return result.toString();
    }

}
