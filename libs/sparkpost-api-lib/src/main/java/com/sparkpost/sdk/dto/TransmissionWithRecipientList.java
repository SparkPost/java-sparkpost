package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for a transmission using a recipient list id (a recipient list stored at
 * the server)
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionWithRecipientList extends TransmissionBase {

	/**
	 * Specify a stored recipient list or specify recipients inline. When using
	 * a stored recipient list, specify the "list_id" as described in Using a
	 * Stored Recipient List. Otherwise, provide the recipients inline using the
	 * fields described in the Recipient List API documentation for Recipient
	 * Attributes.
	 */
	@SerializedName("recipients")
	private StoredRecipientList recipientList = null;

}
