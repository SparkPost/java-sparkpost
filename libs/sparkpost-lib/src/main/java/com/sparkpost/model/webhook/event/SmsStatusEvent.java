package com.sparkpost.model.webhook.event;

import java.util.List;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
SMPP/SMS message produced a status log output
*/

@Data
@EqualsAndHashCode()
public class SmsStatusEvent {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@Description(
		value = "Source numbering plan identification",
		sample = "E164")
	private String smsSrcNpi;

	@Description(
		value = "Status type in an SMS status event",
		sample = "SMSC Delivery")
	private String statType;

	@Description(
		value = "IP pool through which this message was sent",
		sample = "Example-Ip-Pool")
	private String ipPool;

	@Description(
		value = "Status value in an SMS status event",
		sample = "Delivered")
	private String statState;

	@Description(
		value = "IP address of the host to which SparkPost delivered this message; in engagement events, the IP address of the host where the HTTP request originated",
		sample = "127.0.0.1")
	private String ipAddress;

	@Description(
		value = "Type of event this record describes",
		sample = "sms_status")
	private String type;

	@Description(
		value = "Destination numbering plan identification",
		sample = "E164")
	private String smsDstNpi;

	@Description(
		value = "The SMS message payload (in the character set specified in sms_coding)",
		sample = "lol")
	private String smsText;

	@Description(
		value = "SMS source address",
		sample = "1234")
	private String smsSrc;

	@Description(
		value = "SMS destination address",
		sample = "7876712656")
	private String smsDst;

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
		value = "Unmodified, exact response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (17.99.99.99) is in black list")
	private String rawReason;

	@Description(
		value = "Type of number for the destination address",
		sample = "International")
	private String smsDstTon;

	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Protocol by which SparkPost delivered this message",
		sample = "esmtp")
	private String delvMethod;

	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@Description(
		value = "The message ID(s) in the response, assigned by the remote server/SMSC",
		sample = "[\"0000\",\"0001\",\"0002\",\"0003\",\"0004\"]")
	private List<String> smsRemoteids;

	@Description(
		value = "Delivery report latency; interval between message submission and receipt",
		sample = "0.02")
	private String drLatency;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1454442600")
	private String timestamp;

}
