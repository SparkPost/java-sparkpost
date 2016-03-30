
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for attachments and inline images
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileAttributes extends Base {

    public FileAttributes() {

    }

    /**
     * Valid email address
     */
    @Description(
            value = "The MIME type of the file. The value will apply \"as-is\" to the \"Content-Type\" header of the generated MIME part for the file.",
            sample = {"image/jpeg"})
    private String type;

    /**
     * User-friendly name for the email address
     */
    @Description(value = "The name of the file.", sample = {"rainbow.jpg"})
    private String name;

    /**
     * Email address to display in the "To" header instead of address.email (for BCC)
     */
    @Description(
            value = "he content of the file as a Base64 encoded string. The string should not contain \\r\\n line breaks. The SparkPost systems will add line breaks as necessary to ensure the Base64 encoded lines contain no more than 76 characters each.",
            sample = {""})
    private String data;

}
