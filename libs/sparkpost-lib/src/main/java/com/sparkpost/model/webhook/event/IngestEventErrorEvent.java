package com.sparkpost.model.webhook.event;

import java.util.List;
import java.util.Map;
import com.yepher.jsondoc.annotations.Description;
import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Details of an Ingest API processing error
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class IngestEventErrorEvent  extends Base {

	@Description(
		value = "Indicates if an error retryable",
		sample = "false")
	private int retryable;

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

	@Description(
		value = "Type of event this record describes",
		sample = "error")
	private String type;

	@SerializedName("number_duplicates")
	@Description(
		value = "How many events were already processed in a previous batch",
		sample = "350")
	private int numberDuplicates;

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

	@SerializedName("number_failed")
	@Description(
		value = "How many events failed processing",
		sample = "50")
	private int numberFailed;

	@SerializedName("error_type")
	@Description(
		value = "Category of error that was encountered in processing",
		sample = "validation")
	private String errorType;

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "A reference to a failed batch",
		sample = "https://api.sparkpost.com/api/v1/ingest/events/failures/fbd59e4c-1629-4736-803d-201ff9fa8dd6")
	private String href;

	@SerializedName("customer_id")
	@Description(
		value = "SparkPost-customer identifier through which this message was sent",
		sample = "1")
	private String customerId;

	@Description(
		value = "Event date and time, in Unix timestamp format (integer seconds since 00:00:00 GMT 1970-01-01)",
		sample = "1460989507")
	private String timestamp;

}
