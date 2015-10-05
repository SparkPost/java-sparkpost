
package com.sparkpost.model.responses;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.OptionsAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionResponseInfo extends Response {

    @Description(value = "Maximum length - 1024 bytes", sample = {""})
    private String description;

    @Description(value = "When the generation ended", sample = {""})
    @SerializedName("generation_end_time")
    private String generationEndTime;

    @Description(value = "", sample = {""})
    @SerializedName("rcpt_list_total_chunks")
    private int rcptListTotalChunks;

    @Description(value = "The size to use for recipient lists", sample = {""})
    @SerializedName("rcpt_list_chunk_size")
    private int rcptListChunkSize;

    @Description(value = "Number of recipients in transmission", sample = {""})
    @SerializedName("num_rcpts")
    private int numRecipients;

    @Description(value = "Number of emails generated", sample = {""})
    @SerializedName("num_generated")
    private int numGenerated;

    @Description(value = "Transmission options", sample = {""})
    private OptionsAttributes options;

    @Description(value = "Transmission id", sample = {""})
    private String id;

    @Description(value = "Transmission return path", sample = {""})
    @SerializedName("return_path")
    private String returnPath;

    @Description(value = "The start time for the generation", sample = {""})
    @SerializedName("generation_start_time")
    private String generationStartTime;

    @Description(value = "Transmission metadata", sample = {""})
    private String metadata;

    @Description(value = "Transmission names", sample = {""})
    private String name;

    @Description(value = "Number of message that failed to generate", sample = {""})
    @SerializedName("num_failed_gen")
    private int numFailedGen;

    @Description(value = "The recipient list id for this transmission", sample = {""})
    @SerializedName("rcpt_list_id")
    private String rcptListId;

    @Description(value = "Transmission campaign id", sample = {""})
    @SerializedName("campaign_id")
    private String campaignId;

    @Description(value = "Template contents", sample = {""})
    private TemplateContentAttributes content;

}
