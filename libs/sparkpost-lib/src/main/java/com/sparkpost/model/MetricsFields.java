
package com.sparkpost.model;

/**
 * Deliverability Metrics Fields
 * See: https://developers.sparkpost.com/api/#/reference/metrics
 */
public class MetricsFields {

    public static final String DOMAIN = "domain";

    public static final String CAMPAIGN_ID = "campaign_id";

    public static final String TEMPLATE_ID = "template_id";

    public static final String WATCHED_DOMAIN = "watched_domain";

    public static final String TIMESTAMP = "ts";

    /**
     * All Deliverability Fields
     */
    public static final String ALL_FIELDS = "count_targeted,count_injected,count_sent,count_accepted,count_delivered_first,count_delivered_subsequent,count_rendered,count_unique_rendered,count_unique_confirmed_opened,count_clicked,count_unique_clicked,count_bounce,count_hard_bounce,count_soft_bounce,count_block_bounce,count_admin_bounce,count_undetermined_bounce,count_rejected,count_policy_rejection,count_generation_failed,count_generation_rejection,count_inband_bounce,count_outofband_bounce,count_delayed,count_delayed_first,total_msg_volume,count_spam_complaint,total_delivery_time_first,total_delivery_time_subsequent,total_msg_volume";

    public static final String[] ALL_FIELDS_ARRAY = new String[] {
            "count_targeted",
            "count_injected",
            "count_sent",
            "count_accepted",
            "count_delivered_first",
            "count_delivered_subsequent",
            "count_rendered",
            "count_unique_rendered",
            "count_unique_confirmed_opened",
            "count_clicked",
            "count_unique_clicked",
            "count_bounce",
            "count_hard_bounce",
            "count_soft_bounce",
            "count_block_bounce",
            "count_admin_bounce",
            "count_undetermined_bounce",
            "count_rejected",
            "count_policy_rejection",
            "count_generation_failed",
            "count_generation_rejection",
            "count_inband_bounce",
            "count_outofband_bounce",
            "count_delayed",
            "count_delayed_first",
            "total_msg_volume",
            "count_spam_complaint",
            "total_delivery_time_first",
            "total_delivery_time_subsequent",
            "total_msg_volume"

    };

    /**
     * Messages successfully injected into SparkPost and SparkPost Elite as well as rejected by it
     */
    public static final String COUNT_TARGETED = "count_targeted";

    /**
     * Messages injected to or received by SparkPost and SparkPost Elite
     */
    public static final String COUNT_INJECTED = "count_injected";

    /**
     * Messages that SparkPost and SparkPost Elite attempted to deliver, which includes both Deliveries and In-Band Bounces
     */
    public static final String COUNT_SENT = "count_sent";

    /**
     * Messages an ISP or other remote domain accepted (less Out-of-Band Bounces)
     */
    public static final String COUNT_ACCEPTED = "count_accepted";

    /**
     * Messages delivered on the first attempt
     */
    public static final String COUNT_DELIVERED_FIRST = "count_delivered_first";

    /**
     * Messages delivered that required more than one delivery attempt
     */
    public static final String COUNT_DELIVERED_SUBSEQUENT = "count_delivered_subsequent";

    /**
     * Total renderings of a message
     */
    public static final String COUNT_RENDERED = "count_rendered";

    /**
     * Total number of messages that were rendered at least once
     */
    public static final String COUNT_UNIQUE_RENDERED = "count_unique_rendered";

    /**
     * Total number of messages that were rendered or had at least one link selected
     */
    public static final String COUNT_UNIQUE_CONFIRMED_OPENED = "count_unique_confirmed_opened";

    /**
     * Total number of times that links were selected across all messages
     */
    public static final String COUNT_CLICKED = "count_clicked";

    /**
     * Total number of messages which had at least one link selected one or more times
     */
    public static final String COUNT_UNIQUE_CLICKED = "count_unique_clicked";

    /**
     * Total number of bounced messages, which includes both In-Band and Out-of-Band bounces
     */
    public static final String COUNT_BOUNCE = "count_bounce";

    /**
     * Total number of Bounced messages due to hard bounce classification reasons
     */
    public static final String COUNT_HARD_BOUNCE = "count_hard_bounce";

    /**
     * Total number of Bounced messages due to soft bounce classification reasons
     */
    public static final String COUNT_SOFT_BOUNCE = "count_soft_bounce";

    /**
     * Total number of Bounced messages due to an IP block
     */
    public static final String COUNT_BLOCK_BOUNCE = "count_block_bounce";

    /**
     * Total number of Bounced messages due to admin bounce classification reasons, also includes Rejected
     */
    public static final String COUNT_ADMIN_BOUNCE = "count_admin_bounce";

    /**
     * Total number of Bounced messages due to undetermined bounce reasons
     */
    public static final String COUNT_UNDERTERMINED_BOUNCE = "count_undetermined_bounce";

    /**
     * Messages rejected due to policy or that failed to generate
     */
    public static final String COUNT_REJECTED = "count_rejected";

    /**
     * Messages rejected by SparkPost and SparkPost Elite due to policy
     */
    public static final String COUNT_POLICY_REJECTED = "count_policy_rejection";

    /**
     * Message generation failed for an intended recipient
     */
    public static final String COUNT_GENERATION_FAILED = "count_generation_failed";

    /**
     * Messages rejected by SparkPost and SparkPost Elite due to policy
     */
    public static final String COUNT_GENERATION_REJECTION = "count_generation_rejection";

    /**
     * Messages that bounced on delivery attempt during the SMTP session
     */
    public static final String COUNT_INBAND_BOUNCE = "count_inband_bounce";

    /**
     * Messages that the ISP bounced subsequent to a successful delivery
     */
    public static final String COUNT_OUTOFBAND_BOUNCE = "count_outofband_bounce";

    /**
     * Total number of delays due to any temporary failure
     */
    public static final String COUNT_DELAYED = "count_delayed";

    /**
     * Messages delayed on the first delivery attempt
     */
    public static final String COUNT_DELAYED_FIRST = "count_delayed_first";

    /**
     * Number of spam complaints received from an ISP
     */
    public static final String COUNT_SPAM_COMPLAINT = "count_spam_complaint";

    public static final String TOTAL_DELIVERY_TIME_FIRST = "total_delivery_time_first";

    public static final String TOTAL_DELIVERY_TIME_SUBSEQUENT = "total_delivery_time_subsequent";

    /**
     * Total size of delivered messages, in bytes (including attachments)
     */
    public static final String TOTAL_MSG_VOLUME = "total_msg_volume";

}
