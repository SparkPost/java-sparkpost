package com.sparkpost.model.webhook.event;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Relayed message is received by or injected into SparkPost.
*/

@Data
@EqualsAndHashCode()
public class RelayInjectionEvent {

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
		value = "Domain receiving this message",
		sample = "example.com")
	private String routingDomain;

	@Description(
		value = "IP address through which this message was sent",
		sample = "127.0.0.1")
	private String sendingIp;

	@Description(
		value = "IP pool through which this message was sent",
		sample = "Example-Ip-Pool")
	private String ipPool;

	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Type of event this record describes",
		sample = "relay_injection")
	private String type;

	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@Description(
		value = "Actual recipient address used on this message's SMTP envelope",
		sample = "Recipient@example.com")
	private String rawRcptTo;

	@Description(
		value = "Message's size in bytes",
		sample = "1337")
	private String msgSize;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1454442600")
	private String timestamp;

}
