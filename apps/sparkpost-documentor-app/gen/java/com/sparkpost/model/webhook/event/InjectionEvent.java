package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;
import com.yepher.jsondoc.annotations.Description;
import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Message is received by or injected into SparkPost.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class InjectionEvent  extends Base {

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

	@Description(
		value = "Subject line from the email header",
		sample = "Summer deals are here!")
	private String subject;

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
		sample = "injection")
	private String type;

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

	@SerializedName("sms_text")
	@Description(
		value = "The SMS message payload (in the character set specified in sms_coding)",
		sample = "lol")
	private String smsText;

	@SerializedName("sms_src")
	@Description(
		value = "SMS source address",
		sample = "1234")
	private String smsSrc;

	@SerializedName("msg_from")
	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

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

	@SerializedName("sms_coding")
	@Description(
		value = "Data encoding used in the SMS message",
		sample = "ASCII")
	private String smsCoding;

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

	@SerializedName("message_id")
	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

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

	@SerializedName("sms_dst")
	@Description(
		value = "SMS destination address",
		sample = "7876712656")
	private String smsDst;

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

	@SerializedName("sms_src_ton")
	@Description(
		value = "Type of number for the source address",
		sample = "Unknown")
	private String smsSrcTon;

	@SerializedName("sms_dst_ton")
	@Description(
		value = "Type of number for the destination address",
		sample = "International")
	private String smsDstTon;

	@SerializedName("sms_segments")
	@Description(
		value = "Segment number of the log line for large messages sent through multiple SMSes",
		sample = "5")
	private int smsSegments;

	@SerializedName("amp_enabled")
	@Description(
		value = "Indicates whether or not amp format was enabled",
		sample = "true")
	private int ampEnabled;

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

	@SerializedName("open_tracking")
	@Description(
		value = "Indicates whether or not open tracking was enabled",
		sample = "true")
	private int openTracking;

	@SerializedName("rcpt_hash")
	@Description(
		value = "Hashed version of recipient address used on this message's SMTP envelope",
		sample = "2aae6c35c94fcfb415dbe95f408b9ce91ee846ed")
	private String rcptHash;

	@SerializedName("injection_time")
	@Description(
		value = "Time at which this message was injected into SparkPost",
		sample = "2016-04-18T14:25:07.000Z")
	private String injectionTime;

	@Description(
		value = "Indicates if the transmission was marked as transactional",
		sample = "1")
	private String transactional;

	@SerializedName("msg_size")
	@Description(
		value = "Message's size in bytes",
		sample = "1337")
	private String msgSize;

}
