package com.sparkpost.model.webhook.event;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Details of an Ingest API processing success
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class IngestEventSuccessEvent  extends Base {

	@SerializedName("number_succeeded")
	@Description(
		value = "How many events succeeded processing",
		sample = "500")
	private int numberSucceeded;

	@SerializedName("event_id")
	@Description(
		value = "Unique event identifier",
		sample = "0e5cf1fc-cb36-4c39-b695-3651b6ea6563")
	private String eventId;

	@SerializedName("batch_id")
	@Description(
		value = "Universally unique identifier",
		sample = "96500f4d-d4f4-4f1b-8080-02f4682184bb")
	private String batchId;

	@SerializedName("expiration_timestamp")
	@Description(
		value = "The time at which an ingest batch is no longer retrievable",
		sample = "2019-06-16T19:02:09.373Z")
	private String expirationTimestamp;

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Type of event this record describes",
		sample = "success")
	private String type;

	@SerializedName("customer_id")
	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@SerializedName("number_duplicates")
	@Description(
		value = "How many events were already processed in a previous batch",
		sample = "350")
	private int numberDuplicates;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

}
