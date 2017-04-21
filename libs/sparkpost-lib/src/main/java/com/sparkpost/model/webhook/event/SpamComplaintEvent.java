package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Message was classified as spam by the recipient.
*/

@Data
@EqualsAndHashCode()
public class SpamComplaintEvent {

	@Description(
		value = "Version of the template used to construct this message",
		sample = "1")
	private String templateVersion;

	@Description(
		value = "Friendly sender or \"From\" header in the original email",
		sample = "sender@example.com")
	private String friendlyFrom;

	@Description(
		value = "Subject line from the email header",
		sample = "Summer deals are here!")
	private String subject;

	@Description(
		value = "IP pool through which this message was sent",
		sample = "Example-Ip-Pool")
	private String ipPool;

	@Description(
		value = "Tags applied to the message which generated this event",
		sample = "[\"male\",\"US\"]")
	private List<String> rcptTags;

	@Description(
		value = "Type of event this record describes",
		sample = "spam_complaint")
	private String type;

	@Description(
		value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero",
		sample = "2")
	private String numRetries;

	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "Recipient@example.com")
	private String rawRcptTo;

	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@Description(
		value = "Address to which this spam report is to be delivered",
		sample = "abuse.example.com")
	private String reportTo;

	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Transmission which originated this message",
		sample = "65832150921904138")
	private String transmissionId;

	@Description(
		value = "Campaign of which this message was a part",
		sample = "Example Campaign Name")
	private String campaignId;

	@Description(
		value = "Type of spam report entered against this message (see [RFC 5965 § 7.3](http://tools.ietf.org/html/rfc5965#section-7.3))",
		sample = "abuse")
	private String fbtype;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1454442600")
	private String timestamp;

	@Description(
		value = "If configured, additional message log information, in user-defined format",
		sample = "Additional Example Information")
	private String userStr;

	@Description(
		value = "Metadata describing the message recipient",
		sample = "{\"customKey\":\"customValue\"}")
	private Map<String, String> rcptMeta;

	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

	@Description(
		value = "IP address of the host to which SparkPost delivered this message; in engagement events, the IP address of the host where the HTTP request originated",
		sample = "127.0.0.1")
	private String ipAddress;

	@Description(
		value = "Indicates that a recipient address appeared in the Cc or Bcc header or the archive JSON array",
		sample = "cc")
	private String rcptType;

	@Description(
		value = "Delay, expressed in milliseconds, between this message's injection into SparkPost and its delivery to the receiving domain; that is, the length of time this message spent in the outgoing queue",
		sample = "12")
	private String queueTime;

	@Description(
		value = "Address of the entity reporting this message as spam",
		sample = "server.email.com")
	private String reportBy;

	@Description(
		value = "Unique event identifier",
		sample = "92356927693813856")
	private String eventId;

	@Description(
		value = "Domain receiving this message",
		sample = "example.com")
	private String routingDomain;

	@Description(
		value = "IP address through which this message was sent",
		sample = "127.0.0.1")
	private String sendingIp;

	@Description(
		value = "Slug of the template used to construct this message",
		sample = "templ-1234")
	private String templateId;

	@Description(
		value = "Protocol by which SparkPost delivered this message",
		sample = "esmtp")
	private String delvMethod;

	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@Description(
		value = "Message's size in bytes",
		sample = "1337")
	private String msgSize;

}
