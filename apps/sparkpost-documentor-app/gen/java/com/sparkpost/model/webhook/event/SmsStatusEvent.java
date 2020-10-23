package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;
import com.yepher.jsondoc.annotations.Description;
import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
SMPP/SMS message produced a status log output
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class SmsStatusEvent  extends Base {

	@Description(
		value = "Canonicalized text of the response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (a.b.c.d) is in black list")
	private String reason;

	@SerializedName("sms_src_npi")
	@Description(
		value = "Source numbering plan identification",
		sample = "E164")
	private String smsSrcNpi;

	@SerializedName("stat_type")
	@Description(
		value = "Status type in an SMS status event",
		sample = "SMSC Delivery")
	private String statType;

	@SerializedName("ip_pool")
	@Description(
		value = "IP pool through which this message was sent",
		sample = "example-ip-pool")
	private String ipPool;

	@SerializedName("stat_state")
	@Description(
		value = "Status value in an SMS status event",
		sample = "Delivered")
	private String statState;

	@SerializedName("ip_address")
	@Description(
		value = "IP address of the host to which SparkPost delivered this message; in engagement events, the IP address of the host where the HTTP request originated",
		sample = "127.0.0.1")
	private String ipAddress;

	@Description(
		value = "Type of event this record describes",
		sample = "sms_status")
	private String type;

	@SerializedName("sms_dst_npi")
	@Description(
		value = "Destination numbering plan identification",
		sample = "E164")
	private String smsDstNpi;

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

	@SerializedName("sms_dst")
	@Description(
		value = "SMS destination address",
		sample = "7876712656")
	private String smsDst;

	@SerializedName("recv_method")
	@Description(
		value = "Protocol by which SparkPost received this message",
		sample = "esmtp")
	private String recvMethod;

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

	@SerializedName("raw_reason")
	@Description(
		value = "Unmodified, exact response returned by the remote server due to a failed delivery attempt",
		sample = "MAIL REFUSED - IP (17.99.99.99) is in black list")
	private String rawReason;

	@SerializedName("sms_dst_ton")
	@Description(
		value = "Type of number for the destination address",
		sample = "International")
	private String smsDstTon;

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

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

	@SerializedName("sms_remoteids")
	@Description(
		value = "The message ID(s) in the response, assigned by the remote server/SMSC",
		sample = "[\"0000\",\"0001\",\"0002\",\"0003\",\"0004\"]")
	private List<String> smsRemoteids;

	@SerializedName("dr_latency")
	@Description(
		value = "Delivery report latency; interval between message submission and receipt",
		sample = "0.02")
	private String drLatency;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

}
