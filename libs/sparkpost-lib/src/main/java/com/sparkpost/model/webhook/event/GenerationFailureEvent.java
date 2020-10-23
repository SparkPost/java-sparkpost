package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Message generation failed for an intended recipient.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class GenerationFailureEvent  extends Base {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@SerializedName("template_version")
	@Description(
		value = "Version of the template used to construct this message",
		sample = "1")
	private String templateVersion;

	@SerializedName("friendly_from")
	@Description(
		value = "Friendly sender or 'From' header in the original email",
		sample = "sender@example.com")
	private String friendlyFrom;

	@SerializedName("ab_test_id")
	@Description(
		value = "A/B test of which this event was a part.",
		sample = "password-reset")
	private String abTestId;

	@SerializedName("ip_pool")
	@Description(
		value = "IP pool through which this message was sent",
		sample = "example-ip-pool")
	private String ipPool;

	@SerializedName("rcpt_tags")
	@Description(
		value = "Tags applied to the message which generated this event",
		sample = "[\"male\",\"US\"]")
	private List<String> rcptTags;

	@SerializedName("scheduled_time")
	@Description(
		value = "Time at which the email was scheduled to be sent",
		sample = "1588348800")
	private String scheduledTime;

	@Description(
		value = "Type of event this record describes",
		sample = "generation_failure")
	private String type;

	@SerializedName("raw_rcpt_to")
	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rawRcptTo;

	@SerializedName("recv_method")
	@Description(
		value = "Protocol by which SparkPost received this message",
		sample = "esmtp")
	private String recvMethod;

	@SerializedName("rcpt_to")
	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@SerializedName("rcpt_subs")
	@Description(
		value = "Substitutions applied to the template to construct this message",
		sample = "{\"country\":\"US\",\"gender\":\"Female\"}")
	private Map<String, String> rcptSubs;

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@SerializedName("transmission_id")
	@Description(
		value = "Transmission which originated this message",
		sample = "65832150921904138")
	private String transmissionId;

	@SerializedName("campaign_id")
	@Description(
		value = "Campaign of which this message was a part",
		sample = "Example Campaign Name")
	private String campaignId;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

	@SerializedName("ab_test_version")
	@Description(
		value = "A/B test version that was used for this event.",
		sample = "1")
	private String abTestVersion;

	@SerializedName("rcpt_meta")
	@Description(
		value = "Metadata describing the message recipient",
		sample = "{\"customKey\":\"customValue\"}")
	private Map<String, String> rcptMeta;

	@SerializedName("recipient_domain")
	@Description(
		value = "Domain part of the recipient address used on this message's SMTP envelope",
		sample = "example.com")
	private String recipientDomain;

	@SerializedName("event_id")
	@Description(
		value = "Unique event identifier",
		sample = "92356927693813856")
	private String eventId;

	@SerializedName("routing_domain")
	@Description(
		value = "Domain receiving this message",
		sample = "example.com")
	private String routingDomain;

	@SerializedName("sending_ip")
	@Description(
		value = "IP address through which this message was sent",
		sample = "127.0.0.1")
	private String sendingIp;

	@SerializedName("raw_reason")
	@Description(
		value = "Unmodified, exact response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (17.99.99.99) is in black list")
	private String rawReason;

	@SerializedName("error_code")
	@Description(
		value = "Error code by which the remote server described a failed delivery attempt",
		sample = "554")
	private String errorCode;

	@SerializedName("template_id")
	@Description(
		value = "Slug of the template used to construct this message",
		sample = "templ-1234")
	private String templateId;

	@SerializedName("customer_id")
	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@SerializedName("rcpt_hash")
	@Description(
		value = "Hashed version of recipient address used on this message's SMTP envelope",
		sample = "2aae6c35c94fcfb415dbe95f408b9ce91ee846ed")
	private String rcptHash;

	@Description(
		value = "Indicates if the transmission was marked as transactional",
		sample = "1")
	private String transactional;

}
