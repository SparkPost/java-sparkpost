package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;
import com.yepher.jsondoc.annotations.Description;
import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
SparkPost rejected a relayed message or failed to generate a relayed message.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class RelayRejectionEvent  extends Base {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@SerializedName("remote_addr")
	@Description(
		value = "IP address of the host from which SparkPost received this message",
		sample = "127.0.0.1")
	private String remoteAddr;

	@SerializedName("message_id")
	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

	@Description(
		value = "Type of event this record describes",
		sample = "relay_rejection")
	private String type;

	@SerializedName("raw_rcpt_to")
	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rawRcptTo;

	@SerializedName("bounce_class")
	@Description(
		value = "Classification code for a given message (see [Bounce Classification Codes](https://support.sparkpost.com/customer/portal/articles/1929896))",
		sample = "25")
	private String bounceClass;

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

	@SerializedName("event_id")
	@Description(
		value = "Unique event identifier",
		sample = "92356927693813856")
	private String eventId;

	@Description(
		value = "Source of the event",
		sample = "inbound")
	private String origination;

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

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

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

	@SerializedName("injection_time")
	@Description(
		value = "Time at which this message was injected into SparkPost",
		sample = "2016-04-18T14:25:07.000Z")
	private String injectionTime;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

	@SerializedName("relay_id")
	@Description(
		value = "Unique identifier of the Relay Webhook associated with this message (uuid)",
		sample = "123-456-789")
	private String relayId;

}
