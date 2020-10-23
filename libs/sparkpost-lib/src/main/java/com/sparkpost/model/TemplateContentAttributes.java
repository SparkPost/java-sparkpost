
package com.sparkpost.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing the 'content' field in a template
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateContentAttributes extends Base {

    /**
     * ID of the stored template to use
     * Specify this field when using a stored template. Maximum length -- 64
     * bytes
     */
    @Description(value = "ID of the stored template to use. Specify this field when using a stored template. Maximum length -- 64 bytes", sample = {"AbC124"})
    @SerializedName("template_id")
    private String templateId;

    /**
     * Whether or not to use a draft template
     * If this field is set to true and no draft template exists, the
     * transmission will fail.
     */
    @Description(
            value = "Whether or not to use a draft template. If this field is set to true and no draft template exists, the transmission will fail.",
            sample = {"false"})
    @SerializedName("use_draft_template")
    private Boolean useDraftTemplate;

    @Description(value = "HTML Content of email", sample = {"HTML Content"})
    private String html = null;

    @Description(value = "Amp HTML Content of email", sample = {"AMP HTML Content"})
    @SerializedName("amp_html")
    private String ampHtml = null;

    @Description(value = "Text content for the email's text/plain MIME part", sample = {"Text Content"})
    private String text = null;

    @Description(
            value = "Subject of email. Expected in the UTF-8 charset without RFC2047 encoding. Substitution syntax is supported.",
            sample = {"Subject of message"})
    private String subject = null;

    @Description(
            value = "Address used to compose the email's \"From\" header",
            sample = {"\"from\" : { \"name\" : \"My Company\", \"email\" : \"deals@company.com\" }"})
    private AddressAttributes from = null;

    @Description(value = "Email address used to compose the email's \"Reply-To\" header", sample = {"reply_name@example.com"})
    @SerializedName("reply_to")
    private String replyTo = null;

    @Description(value = "Extra email headers to send", sample = {"Dictionary of Email Headers"})
    private Map<String, String> headers = null;

    /**
     * Attachments for a transmission
     */
    @Description(value = "List of AttachmentAttributes. Attachments are not valid with templateId.", sample = {""})
    private List<AttachmentAttributes> attachments = null;

    /**
     * Inline images for a transmission
     */
    @Description(value = "List of InlineImageAttributes. Inline images are not valid with templateId.", sample = {""})
    @SerializedName("inline_images")
    private List<InlineImageAttributes> inlineImages = null;

    /**
     * Alternatively, the email_rfc822 may be used *instead* of all the other fields.
     * The email_rfc822 field is mutually exclusive with all of the other fields in this class.
     */
    @Description(
            value = "Alternatively, the email_rfc822 may be used *instead* of all the other fields. The email_rfc822 field is mutually exclusive with all of the above fields.",
            sample = {""})
    @SerializedName("email_rfc822")
    private String emailRFC822 = null;

}
