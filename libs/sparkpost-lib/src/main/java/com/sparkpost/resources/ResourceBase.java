package com.sparkpost.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResourceBase {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final Gson GSON = new GsonBuilder().setDateFormat(DATE_TIME_FORMAT).create();

    protected static String getObjectAsJsonForClass(final Object object, final Class tClass) {
        return GSON.toJson(object, tClass);
    }

}
