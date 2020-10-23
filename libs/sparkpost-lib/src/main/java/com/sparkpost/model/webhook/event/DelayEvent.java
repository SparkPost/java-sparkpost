package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Remote MTA has temporarily rejected a message.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class DelayEvent  extends Base {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@Description(
		value = "Subject line from the email header",
		sample = "Summer deals are here!")
	private String subject;

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
		sample = "delay")
	private String type;

	@SerializedName("num_retries")
	@Description(
		value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero",
		sample = "2")
	private String numRetries;

	@SerializedName("sms_dst_npi")
	@Description(
		value = "Destination numbering plan identification",
		sample = "E164")
	private String smsDstNpi;

	@SerializedName("raw_rcpt_to")
	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rawRcptTo;

	@SerializedName("sms_src")
	@Description(
		value = "SMS source address",
		sample = "1234")
	private String smsSrc;

	@SerializedName("recv_method")
	@Description(
		value = "Protocol by which SparkPost received this message",
		sample = "esmtp")
	private String recvMethod;

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

	@SerializedName("click_tracking")
	@Description(
		value = "Indicates whether or not click tracking was enabled",
		sample = "true")
	private int clickTracking;

	@SerializedName("rcpt_meta")
	@Description(
		value = "Metadata describing the message recipient",
		sample = "{\"customKey\":\"customValue\"}")
	private Map<String, String> rcptMeta;

	@SerializedName("queue_time")
	@Description(
		value = "Delay, expressed in milliseconds, between this message's injection into SparkPost and its delivery to the receiving domain; that is, the length of time this message spent in the outgoing queue",
		sample = "12")
	private String queueTime;

	@SerializedName("sms_dst")
	@Description(
		value = "SMS destination address",
		sample = "7876712656")
	private String smsDst;

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

	@SerializedName("amp_enabled")
	@Description(
		value = "Indicates whether or not amp format was enabled",
		sample = "true")
	private int ampEnabled;

	@SerializedName("error_code")
	@Description(
		value = "Error code by which the remote server described a failed delivery attempt",
		sample = "554")
	private String errorCode;

	@SerializedName("rcpt_hash")
	@Description(
		value = "Hashed version of recipient address used on this message's SMTP envelope",
		sample = "2aae6c35c94fcfb415dbe95f408b9ce91ee846ed")
	private String rcptHash;

	@Description(
		value = "Indicates if the transmission was marked as transactional",
		sample = "1")
	private String transactional;

	@SerializedName("sms_src_npi")
	@Description(
		value = "Source numbering plan identification",
		sample = "E164")
	private String smsSrcNpi;

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

	@SerializedName("ip_pool")
	@Description(
		value = "IP pool through which this message was sent",
		sample = "example-ip-pool")
	private String ipPool;

	@SerializedName("bounce_class")
	@Description(
		value = "Classification code for a given message (see [Bounce Classification Codes](https://support.sparkpost.com/customer/portal/articles/1929896))",
		sample = "1")
	private String bounceClass;

	@SerializedName("msg_from")
	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

	@SerializedName("rcpt_to")
	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

	@SerializedName("sms_coding")
	@Description(
		value = "Data encoding used in the SMS message",
		sample = "ASCII")
	private String smsCoding;

	@SerializedName("outbound_tls")
	@Description(
		value = "Whether a message was sent using TLS or not. Specifies if a message wasn't sent with TLS either because no TLS was offered or TLS negotiation failed. 1 means TLS was used, 0 means TLS was not offered, -1 means TLS negotiation failed",
		sample = "-1")
	private String outboundTls;

	@SerializedName("message_id")
	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

	@SerializedName("ip_address")
	@Description(
		value = "IP address of the host to which SparkPost delivered this message; in engagement events, the IP address of the host where the HTTP request originated",
		sample = "127.0.0.1")
	private String ipAddress;

	@SerializedName("initial_pixel")
	@Description(
		value = "Indicates whether or not initial open pixel tracking was enabled",
		sample = "true")
	private int initialPixel;

	@SerializedName("rcpt_type")
	@Description(
		value = "Indicates that a recipient address appeared in the Cc or Bcc header or the archive JSON array",
		sample = "cc")
	private String rcptType;

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

	@SerializedName("sms_src_ton")
	@Description(
		value = "Type of number for the source address",
		sample = "Unknown")
	private String smsSrcTon;

	@SerializedName("device_token")
	@Description(
		value = "Token of the device / application targeted by this PUSH notification message. Applies only when delv_method is gcm or apn.",
		sample = "45c19189783f867973f6e6a5cca60061ffe4fa77c547150563a1192fa9847f8a")
	private String deviceToken;

	@SerializedName("sms_dst_ton")
	@Description(
		value = "Type of number for the destination address",
		sample = "International")
	private String smsDstTon;

	@SerializedName("template_id")
	@Description(
		value = "Slug of the template used to construct this message",
		sample = "templ-1234")
	private String templateId;

	@SerializedName("delv_method")
	@Description(
		value = "Protocol by which SparkPost delivered this message",
		sample = "esmtp")
	private String delvMethod;

	@SerializedName("customer_id")
	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@SerializedName("open_tracking")
	@Description(
		value = "Indicates whether or not open tracking was enabled",
		sample = "true")
	private int openTracking;

	@SerializedName("injection_time")
	@Description(
		value = "Time at which this message was injected into SparkPost",
		sample = "2016-04-18T14:25:07.000Z")
	private String injectionTime;

	@SerializedName("msg_size")
	@Description(
		value = "Message's size in bytes",
		sample = "1337")
	private String msgSize;

}
