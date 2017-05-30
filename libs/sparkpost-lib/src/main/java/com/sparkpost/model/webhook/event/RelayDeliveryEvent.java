
package com.sparkpost.model.webhook.event;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Remote HTTP Endpoint acknowledged receipt of a relayed message.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RelayDeliveryEvent extends AbstractWebhookEvent {

    @SerializedName("queue_time")
    @Description(
            value = "Delay, expressed in milliseconds, between this message's injection into SparkPost and its delivery to the receiving domain; that is, the length of time this message spent in the outgoing queue",
            sample = "12")
    private String queueTime;

    @SerializedName("msg_from")
    @Description(value = "Sender address used on this message's SMTP envelope", sample = "sender@example.com")
    private String msgFrom;

    @SerializedName("event_id")
    @Description(value = "Unique event identifier", sample = "92356927693813856")
    private String eventId;

    @SerializedName("routing_domain")
    @Description(value = "Domain receiving this message", sample = "example.com")
    private String routingDomain;

    @SerializedName("sending_ip")
    @Description(value = "IP address through which this message was sent", sample = "127.0.0.1")
    private String sendingIp;

    @SerializedName("ip_pool")
    @Description(value = "IP pool through which this message was sent", sample = "Example-Ip-Pool")
    private String ipPool;

    @SerializedName("subaccount_id")
    @Description(value = "Unique subaccount identifier.", sample = "101")
    private String subaccountId;

    @SerializedName("delv_method")
    @Description(value = "Protocol by which SparkPost delivered this message", sample = "esmtp")
    private String delvMethod;

    @Description(value = "Type of event this record describes", sample = "relay_delivery")
    private String type;

    @SerializedName("customer_id")
    @Description(value = "SparkPost-customer identifier through which this message was sent", sample = "1")
    private String customerId;

    @SerializedName("num_retries")
    @Description(value = "Number of failed attempts before this message was successfully delivered; when the first attempt succeeds, zero", sample = "2")
    private String numRetries;

    @Description(value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)", sample = "1454442600")
    private String timestamp;

}
