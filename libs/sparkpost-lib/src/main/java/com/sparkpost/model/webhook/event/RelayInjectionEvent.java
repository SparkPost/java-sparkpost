package com.sparkpost.model.webhook.event;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Relayed message is received by or injected into SparkPost.
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class RelayInjectionEvent  extends Base {

	@SerializedName("ip_pool")
	@Description(
		value = "IP pool through which this message was sent",
		sample = "example-ip-pool")
	private String ipPool;

	@SerializedName("message_id")
	@Description(
		value = "SparkPost-cluster-wide unique identifier for this message",
		sample = "000443ee14578172be22")
	private String messageId;

	@Description(
		value = "Type of event this record describes",
		sample = "relay_injection")
	private String type;

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

	@SerializedName("recv_method")
	@Description(
		value = "Protocol by which SparkPost received this message",
		sample = "esmtp")
	private String recvMethod;

	@SerializedName("event_id")
	@Description(
		value = "Unique event identifier",
		sample = "92356927693813856")
	private String eventId;

	@SerializedName("rcpt_to")
	@Description(
		value = "Lowercase version of recipient address used on this message's SMTP envelope",
		sample = "recipient@example.com")
	private String rcptTo;

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

	@Description(
		value = "Source of the event",
		sample = "inbound")
	private String origination;

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

	@SerializedName("msg_size")
	@Description(
		value = "Message's size in bytes",
		sample = "1337")
	private String msgSize;

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
