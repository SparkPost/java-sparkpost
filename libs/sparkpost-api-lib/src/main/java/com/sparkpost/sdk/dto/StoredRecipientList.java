package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This is used when specifying a stored recipient list in the transmission.
 * Note that this attribute should not be present when specifying recipients
 * inline.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StoredRecipientList extends Base {

	/**
	 * Identifier of the stored recipient list to use
	 * 
	 * Specify this field when using a stored recipient list.
	 */
	@SerializedName("list_id")
	private String listId = null;

}
