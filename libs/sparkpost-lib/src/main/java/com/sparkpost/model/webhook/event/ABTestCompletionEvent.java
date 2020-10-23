package com.sparkpost.model.webhook.event;

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
Results of an A/B test
*/

@Data
@EqualsAndHashCode(callSuper = true)
public class ABTestCompletionEvent  extends Base {

	@SerializedName("event_id")
	@Description(
		value = "Unique event identifier",
		sample = "0e5cf1fc-cb36-4c39-b695-3651b6ea6563")
	private String eventId;

	@SerializedName("ab_test")
	@Description(
		value = "Metadata describing an A/B test",
		sample = "{\"engagement_metric\":\"count_unique_clicked\",\"test_mode\":\"bayesian\",\"name\":\"Password Reset\",\"default_template\":{\"count_unique_clicked\":10,\"count_accepted\":50,\"template_id\":\"templ-1234\",\"engagement_rate\":0.2},\"id\":\"password-reset\",\"variants\":[{\"count_unique_clicked\":10,\"count_accepted\":50,\"template_id\":\"templ-5678\",\"engagement_rate\":0.2}],\"winning_template_id\":\"templ-1234\",\"version\":1}")
	private Map<String, String> abTest;

	@SerializedName("subaccount_id")
	@Description(
		value = "Unique subaccount identifier.",
		sample = "101")
	private String subaccountId;

	@Description(
		value = "Type of event this record describes",
		sample = "ab_test_completed")
	private String type;

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
