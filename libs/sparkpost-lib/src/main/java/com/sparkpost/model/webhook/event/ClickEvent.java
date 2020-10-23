package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Recipient clicked a tracked link in a message, thus prompting a redirect through the SparkPost click-tracking server to the link's destination.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class ClickEvent  extends Base {

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
		sample = "click")
	private String type;

	@SerializedName("num_retries")
	@Description(
		value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero",
		sample = "2")
	private String numRetries;

	@SerializedName("raw_rcpt_to")
	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rawRcptTo;

	@SerializedName("msg_from")
	@Description(
		value = "Sender address used on this message's SMTP envelope",
		sample = "sender@example.com")
	private String msgFrom;

	@SerializedName("geo_ip")
	@Description(
		value = "Geographic location based on the IP address, including latitude, longitude, city, country, region, and zip",
		sample = "{\"zip\":21046,\"country\":\"US\",\"city\":\"Columbia\",\"latitude\":39.1749,\"region\":\"MD\",\"postal_code\":\"21046\",\"longitude\":-76.8375}")
	private Map<String, String> geoIp;

	@SerializedName("rcpt_to")
	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

	@SerializedName("target_link_name")
	@Description(
		value = "Name of the link for which a click event was generated",
		sample = "Example Link Name")
	private String targetLinkName;

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

	@SerializedName("user_agent")
	@Description(
		value = "Value of the browser's User-Agent header",
		sample = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36")
	private String userAgent;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

	@SerializedName("ab_test_version")
	@Description(
		value = "A/B test version that was used for this event.",
		sample = "1")
	private String abTestVersion;

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

	@SerializedName("queue_time")
	@Description(
		value = "Delay, expressed in milliseconds, between this message's injection into SparkPost and its delivery to the receiving domain; that is, the length of time this message spent in the outgoing queue",
		sample = "12")
	private String queueTime;

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

	@SerializedName("amp_enabled")
	@Description(
		value = "Indicates whether or not amp format was enabled",
		sample = "true")
	private int ampEnabled;

	@SerializedName("target_link_url")
	@Description(
		value = "URL of the link for which a click event was generated",
		sample = "http://example.com")
	private String targetLinkUrl;

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
