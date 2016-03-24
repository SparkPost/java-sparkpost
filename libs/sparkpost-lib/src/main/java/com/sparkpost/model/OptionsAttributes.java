
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing the 'options' field in a template.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionsAttributes extends Base {

    /**
     * Delay generation of messages until this datetime. For additional information, see Scheduled Transmissions.
     * defaults to immediate generation
     * Format YYYY-MM-DDTHH:MM:SS+-HH:MM or "now". Example: '2015-02-11T08:00:00-04:00'.
     */
    @Description(value = "Format YYYY-MM-DDTHH:MM:SS+-HH:MM or \"now\".", sample = {"2015-02-11T08:00:00-04:00"})
    @SerializedName("start_time")
    private String startTime;

    /**
     * Whether open tracking is enabled for this transmission
     * If not specified, the setting at template level is used, or defaults to
     * true.
     */
    @Description(value = "Whether open tracking is enabled for this transmission. defaults to true", sample = {""})
    @SerializedName("open_tracking")
    private Boolean openTracking;

    /**
     * Whether click tracking is enabled for this transmission
     * If not specified, the setting at template level is used, or defaults to
     * true.
     */
    @Description(value = "Whether click tracking is enabled for this transmission. defaults to true", sample = {""})
    @SerializedName("click_tracking")
    private Boolean clickTracking = true;

    /**
     * Whether message is transactional or non-transactional for unsubscribe and
     * suppression purposes
     * If not specified, the setting at template level is used, or defaults to
     * false.
     */
    @Description(value = "Whether message is transactional or non-transactional for unsubscribe and suppression purposes", sample = {""})
    private Boolean transactional = false;

    /**
     * Unlike most other options, this flag is omitted on a GET transmission
     * response unless the flag's value is true.
     * Whether or not to ignore customer suppression rules, for this
     * transmission only. Only applicable if your configuration supports this
     * parameter. ( Note: SparkPost Elite only )
     */
    @Description(value = "Unlike most other options, this flag is omitted on a GET transmission response unless the flag's value is true.", sample = {""})
    @SerializedName("skip_suppression")
    private Boolean skipSuppression;

    /**
     * Whether or not to perform CSS inlining in HTML content
     * Defaults to false
     */
    @Description(value = "Whether or not to perform CSS inlining in HTML content.", sample = {""})
    @SerializedName("inline_css")
    private Boolean inlineCss;
}
