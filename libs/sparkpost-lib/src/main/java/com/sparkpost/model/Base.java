
package com.sparkpost.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Base class for all DTOs. DO NOT USE DIRECTLY. This base class takes care of
 * the JSON serialization.
 */
public class Base {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder().setDateFormat(DATE_TIME_FORMAT);

    /**
     * Generate JSON for this request
     *
     * @return json of object
     */
    public String toJson() {
        return toJson(true);
    }

    /**
     * @param prettyPrint
     *            true to render json pretty printed
     * @return json of object
     */
    public String toJson(boolean prettyPrint) {
        Gson gson;
        if (prettyPrint) {
            gson = GSON_BUILDER.setPrettyPrinting().create();
        } else {
            gson = GSON_BUILDER.create();
        }

        return gson.toJson(this);
    }

    /**
     * Generate JSON from this object for required type.
     * 
     * @param tClass
     *            - target Class.
     * @return json of object.
     */
    @SuppressWarnings("rawtypes")
    public String toJson(Class tClass) {
        return GSON_BUILDER.setPrettyPrinting().create().toJson(this, tClass);
    }
}
