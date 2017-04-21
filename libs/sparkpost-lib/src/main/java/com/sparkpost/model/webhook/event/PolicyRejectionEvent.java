package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Due to policy, SparkPost rejected a message or failed to generate a message.
*/

@Data
@EqualsAndHashCode()
public class PolicyRejectionEvent {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@Description(
		value = "IP address of the host from which SparkPost received this message",
		sample = "127.0.0.1")
	private String remoteAddr;

	@Description(
		value = "Version of the template used to construct this message",
		sample = "1")
	private String templateVersion;

	@Description(
		value = "Friendly sender or \"From\" header in the original email",
		sample = "sender@example.com")
	private String friendlyFrom;

	@Description(
		value = "Metadata describing the message recipient",
		sample = "{\"customKey\":\"customValue\"}")
	private Map<String, String> rcptMeta;

	@Description(
		value = "Tags applied to the message which generated this event",
		sample = "[\"male\",\"US\"]")
	private List<String> rcptTags;

	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

	@Description(
		value = "Type of event this record describes",
		sample = "policy_rejection")
	private String type;

	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "Recipient@example.com")
	private String rawRcptTo;

	@Description(
		value = "Indicates that a recipient address appeared in the Cc or Bcc header or the archive JSON array",
		sample = "cc")
	private String rcptType;

	@Description(
		value = "Classification code for a given message (see [Bounce Classification Codes](https://support.sparkpost.com/customer/portal/articles/1929896))",
		sample = "1")
	private String bounceClass;

	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

	@Description(
		value = "Unique event identifier",
		sample = "92356927693813856")
	private String eventId;

	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@Description(
		value = "IP address through which this message was sent",
		sample = "127.0.0.1")
	private String sendingIp;

	@Description(
		value = "Unmodified, exact response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (17.99.99.99) is in black list")
	private String rawReason;

	@Description(
		value = "Error code by which the remote server described a failed delivery attempt",
		sample = "554")
	private String errorCode;

	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Slug of the template used to construct this message",
		sample = "templ-1234")
	private String templateId;

	@Description(
		value = "Transmission which originated this message",
		sample = "65832150921904138")
	private String transmissionId;

	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@Description(
		value = "Campaign of which this message was a part",
		sample = "Example Campaign Name")
	private String campaignId;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1454442600")
	private String timestamp;

}
