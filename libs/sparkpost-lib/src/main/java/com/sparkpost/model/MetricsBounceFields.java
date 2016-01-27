
package com.sparkpost.model;

public class MetricsBounceFields {

    /**
     * These are all the fields that can be used in a request
     */
    public static final String ALL_FIELDS = "count_bounce,count_inband_bounce,count_outofband_bounce";

    /**
     * This is all the fields available in a response
     */
    public static final String[] ALL_FIELDS_ARRAY = new String[] {
            "bounce_class_name",
            "bounce_class_description",
            "bounce_category_name",
            "classification_id",
            "count_bounce",
            "count_inband_bounce",
            "count_outofband_bounce",
            "domain",
            "reason"};

    public static final String BOUNCE_CLASS_NAME = "bounce_class_name";
    public static final String BOUNCE_CLASS_DESCRIPTION = "bounce_class_description";
    public static final String BOUNCE_CATEGORY_NAME = "bounce_category_name";
    public static final String CLASSIFICATION_ID = "classification_id";
    public static final String COUNT_BOUNCE = "count_bounce";
    public static final String COUNT_INBAND_BOUNCE = "count_inband_bounce";
    public static final String COUNT_OUTOFBAND_BOUNCE = "count_outofband_bounce";

    public static final String REASON = "reason";

    public static final String DOMAIN = "domain";
}
