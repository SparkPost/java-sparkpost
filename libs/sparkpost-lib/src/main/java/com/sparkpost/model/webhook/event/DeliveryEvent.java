
package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Remote MTA acknowledged receipt of a message.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryEvent extends AbstractWebhookEvent {

    @SerializedName("sms_src_npi")
    @Description(value = "Source numbering plan identification", sample = "E164")
    private String smsSrcNpi;

    @SerializedName("template_version")
    @Description(value = "Version of the template used to construct this message", sample = "1")
    private String templateVersion;

    @SerializedName("friendly_from")
    @Description(value = "Friendly sender or \"From\" header in the original email", sample = "sender@example.com")
    private String friendlyFrom;

    @Description(value = "Subject line from the email header", sample = "Summer deals are here!")
    private String subject;

    @SerializedName("ip_pool")
    @Description(value = "IP pool through which this message was sent", sample = "Example-Ip-Pool")
    private String ipPool;

    @SerializedName("rcpt_tags")
    @Description(value = "Tags applied to the message which generated this event", sample = "[\"male\",\"US\"]")
    private List<String> rcptTags;

    @Description(value = "Type of event this record describes", sample = "delivery")
    private String type;

    @SerializedName("num_retries")
    @Description(value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero", sample = "2")
    private String numRetries;

    @SerializedName("sms_dst_npi")
    @Description(value = "Destination numbering plan identification", sample = "E164")
    private String smsDstNpi;

    @SerializedName("raw_rcpt_to")
    @Description(value = "Actual recipient address used on this message's SMTP envelope", sample = "Recipient@example.com")
    private String rawRcptTo;

    @SerializedName("sms_src")
    @Description(value = "SMS source address", sample = "1234")
    private String smsSrc;

    @SerializedName("msg_from")
    @Description(value = "Sender address used on this message's SMTP envelope", sample = "sender@example.com")
    private String msgFrom;

    @SerializedName("rcpt_to")
    @Description(value = "Lowercase version of recipient address used on this message's SMTP envelope", sample = "recipient@example.com")
    private String rcptTo;

    @SerializedName("subaccount_id")
    @Description(value = "Unique subaccount identifier.", sample = "101")
    private String subaccountId;

    @SerializedName("transmission_id")
    @Description(value = "Transmission which originated this message", sample = "65832150921904138")
    private String transmissionId;

    @SerializedName("sms_remoteids")
    @Description(value = "The message ID(s) in the response, assigned by the remote server/SMSC", sample = "[\"0000\",\"0001\",\"0002\",\"0003\",\"0004\"]")
    private List<String> smsRemoteids;

    @SerializedName("campaign_id")
    @Description(value = "Campaign of which this message was a part", sample = "Example Campaign Name")
    private String campaignId;

    @Description(value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)", sample = "1454442600")
    private String timestamp;

    @SerializedName("sms_coding")
    @Description(value = "Data encoding used in the SMS message", sample = "ASCII")
    private String smsCoding;

    @SerializedName("rcpt_meta")
    @Description(value = "Metadata describing the message recipient", sample = "{\"customKey\":\"customValue\"}")
    private Map<String, String> rcptMeta;

    @SerializedName("message_id")
    @Description(value = "SparkPost-cluster-wide unique identifier for this message", sample = "000443ee14578172be22")
    private String messageId;

    @SerializedName("ip_address")
    @Description(
            value = "IP address of the host to which SparkPost delivered this message; in engagement events, the IP address of the host where the HTTP request originated",
            sample = "127.0.0.1")
    private String ipAddress;

    @SerializedName("rcpt_type")
    @Description(value = "Indicates that a recipient address appeared in the Cc or Bcc header or the archive JSON array", sample = "cc")
    private String rcptType;

    @SerializedName("queue_time")
    @Description(
            value = "Delay, expressed in milliseconds, between this message's injection into SparkPost and its delivery to the receiving domain; that is, the length of time this message spent in the outgoing queue",
            sample = "12")
    private String queueTime;

    @SerializedName("sms_dst")
    @Description(value = "SMS destination address", sample = "7876712656")
    private String smsDst;

    @SerializedName("event_id")
    @Description(value = "Unique event identifier", sample = "92356927693813856")
    private String eventId;

    @SerializedName("routing_domain")
    @Description(value = "Domain receiving this message", sample = "example.com")
    private String routingDomain;

    @SerializedName("sending_ip")
    @Description(value = "IP address through which this message was sent", sample = "127.0.0.1")
    private String sendingIp;

    @SerializedName("sms_src_ton")
    @Description(value = "Type of number for the source address", sample = "Unknown")
    private String smsSrcTon;

    @SerializedName("device_token")
    @Description(
            value = "Token of the device / application targeted by this PUSH notification message. Applies only when delv_method is gcm or apn.",
            sample = "45c19189783f867973f6e6a5cca60061ffe4fa77c547150563a1192fa9847f8a")
    private String deviceToken;

    @SerializedName("sms_dst_ton")
    @Description(value = "Type of number for the destination address", sample = "International")
    private String smsDstTon;

    @SerializedName("sms_segments")
    @Description(value = "Segment number of the log line for large messages sent through multiple SMSes", sample = "5")
    private int smsSegments;

    @SerializedName("template_id")
    @Description(value = "Slug of the template used to construct this message", sample = "templ-1234")
    private String templateId;

    @SerializedName("delv_method")
    @Description(value = "Protocol by which SparkPost delivered this message", sample = "esmtp")
    private String delvMethod;

    @SerializedName("customer_id")
    @Description(value = "SparkPost-customer identifier through which this message was sent", sample = "1")
    private String customerId;

    @SerializedName("msg_size")
    @Description(value = "Message's size in bytes", sample = "1337")
    private String msgSize;

}
