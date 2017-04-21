package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Remote MTA acknowledged receipt of a message.
*/

@Data
@EqualsAndHashCode()
public class DeliveryEvent {

	@Description(
		value = "Source numbering plan identification",
		sample = "E164")
	private String smsSrcNpi;

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
		sample = "delivery")
	private String type;

	@Description(
		value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero",
		sample = "2")
	private String numRetries;

	@Description(
		value = "Destination numbering plan identification",
		sample = "E164")
	private String smsDstNpi;

	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "Recipient@example.com")
	private String rawRcptTo;

	@Description(
		value = "SMS source address",
		sample = "1234")
	private String smsSrc;

	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Transmission which originated this message",
		sample = "65832150921904138")
	private String transmissionId;

	@Description(
		value = "The message ID(s) in the response, assigned by the remote server/SMSC",
		sample = "[\"0000\",\"0001\",\"0002\",\"0003\",\"0004\"]")
	private List<String> smsRemoteids;

	@Description(
		value = "Campaign of which this message was a part",
		sample = "Example Campaign Name")
	private String campaignId;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1454442600")
	private String timestamp;

	@Description(
		value = "Data encoding used in the SMS message",
		sample = "ASCII")
	private String smsCoding;

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
		value = "SMS destination address",
		sample = "7876712656")
	private String smsDst;

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
		value = "Type of number for the source address",
		sample = "Unknown")
	private String smsSrcTon;

	@Description(
		value = "Token of the device / application targeted by this PUSH notification message. Applies only when delv_method is gcm or apn.",
		sample = "45c19189783f867973f6e6a5cca60061ffe4fa77c547150563a1192fa9847f8a")
	private String deviceToken;

	@Description(
		value = "Type of number for the destination address",
		sample = "International")
	private String smsDstTon;

	@Description(
		value = "Segment number of the log line for large messages sent through multiple SMSes",
		sample = "5")
	private int smsSegments;

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
