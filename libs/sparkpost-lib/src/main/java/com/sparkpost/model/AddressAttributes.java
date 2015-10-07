
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing an address (email, name, header_to)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddressAttributes extends Base {

    public AddressAttributes() {

    }

    public AddressAttributes(String email) {
        this.email = email;
    }

    public AddressAttributes(String email, String name, String headerTo) {
        this.email = email;
        this.name = name;
        this.headerTo = headerTo;
    }

    /**
     * Valid email address
     */
    @Description(value = "Valid email address", sample = {"someone@example.com"})
    private String email;

    /**
     * User-friendly name for the email address
     */
    @Description(value = "User-friendly name for the email address", sample = {"John Doe"})
    private String name;

    /**
     * Email address to display in the "To" header instead of address.email (for BCC)
     */
    @Description(value = "Email address to display in the \"To\" header instead of address.email (for BCC)", sample = {"someone@example.com"})
    @SerializedName("header_to")
    private String headerTo;

}
