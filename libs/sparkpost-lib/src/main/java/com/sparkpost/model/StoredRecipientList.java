
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This is used when specifying a stored recipient list in the transmission.
 * Note that this attribute should not be present when specifying recipients
 * inline.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StoredRecipientList extends Base {

    /**
     * Identifier of the stored recipient list to use
     * Specify this field when using a stored recipient list.
     */
    @Description(value = "Identifier of the stored recipient list to use. Specify this field when using a stored recipient list.", sample = {"AbC123"})
    @SerializedName("list_id")
    private String listId = null;

}
