
package com.sparkpost.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Base class for all DTOs. DO NOT USE DIRECTLY. This base class takes care of
 * the JSON serialization.
 */
public class Base {

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
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson;
        if (prettyPrint) {
            gson = gsonBuilder.setPrettyPrinting().create();
        } else {
            gson = gsonBuilder.create();
        }

        return gson.toJson(this);
    }
}
