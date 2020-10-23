
package com.sparkpost.documentor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GenerateWebhookEventPojos {

    private static final String WEBHOOK_DOCS_URL = "https://api.sparkpost.com/api/v1/webhooks/events/documentation";
    private static final String OUTPUT_PACKAGE = "com.sparkpost.model.webhook.event";

    private String javaOutputPath = "gen/java/" + OUTPUT_PACKAGE.replaceAll("\\.", "/");

    public static void main(String[] args) throws JSONException, MalformedURLException, IOException {
        GenerateWebhookEventPojos generator = new GenerateWebhookEventPojos();
        generator.processInput();
    }

    private void processInput() throws JSONException, MalformedURLException, IOException {

        JSONObject json = new JSONObject(IOUtils.toString(new URL(WEBHOOK_DOCS_URL), Charset.forName("UTF-8")));
        //System.out.println("Fetched: " + json);

        JSONObject results = json.getJSONObject("results");

        Set<String> keySet = results.keySet();
        for (String key : keySet) {

            JSONObject events = results.getJSONObject(key);
            //String description = events.getString("description");
            //String displayName = events.getString("display_name");

            JSONObject subEventTypes = events.getJSONObject("events");
            Set<String> eventInfo = subEventTypes.keySet();
            System.out.println("package " + OUTPUT_PACKAGE + "." + convertToCamelCase(key, false));
            for (String eventName : eventInfo) {
                //System.out.println("\tclass " + convertToCamelCase(eventName, true));
                JSONObject classInfo = subEventTypes.getJSONObject(eventName);
                String contents = renderEventClass(classInfo);

                // Write class to file
                //writeFile(String contents, String directoryName, String fileName)
                String fileName = convertToCamelCase(classInfo.getString("display_name"), true) + "Event.java";
                writeFile(contents, this.javaOutputPath, fileName);
            }
        }
    }

    public String renderEventClass(JSONObject eventInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append("package " + OUTPUT_PACKAGE + ";\n\n");
        //System.out.println("TODO: render " + eventInfo);

        builder.append("import java.util.List;\n");
        builder.append("import java.util.Map;\n");
        builder.append("import com.yepher.jsondoc.annotations.Description;\n");
        builder.append("import com.google.gson.annotations.SerializedName;\n");
        builder.append("import com.sparkpost.model.Base;\n");
        builder.append("import lombok.Data;\n");
        builder.append("import lombok.EqualsAndHashCode;\n");

        builder.append("\n\n/**\n").append(eventInfo.optString("description").trim()).append("\n*/\n\n");
        builder.append("@Data\n");
        builder.append("@EqualsAndHashCode(callSuper = true)\n");
        builder.append("public class " + convertToCamelCase(eventInfo.getString("display_name"), true) + "Event  extends Base {\n\n");

        JSONObject fields = eventInfo.getJSONObject("event");
        Set<String> fieldNames = fields.keySet();

        for (String fieldName : fieldNames) {
            JSONObject field = fields.getJSONObject(fieldName);

            if (fieldName.contains("_")) {
                builder.append("\t@SerializedName(\"" + fieldName + "\")\n");
            }

            //@Description(
            //        value = "A suppression list - or exclusion list, as it is sometimes called - stores a recipient's opt-out preferences.",
            //        sample = {"Array of SuppressionListEntrys"})
            builder.append("\t@Description(\n");
            builder.append("\t\tvalue = \"" + formatSample(field.getString("description")) + "\",\n");

            String prettyFieldName = convertToCamelCase(fieldName, false);
            if (isFieldArray(field, "sampleValue")) {
                builder.append("\t\tsample = \"" + formatSample(field.getJSONArray("sampleValue").toString()) + "\")\n");
                builder.append("\tprivate List<String> " + prettyFieldName + ";\n");

            } else if (isFieldObject(field, "sampleValue")) {
                builder.append("\t\tsample = \"" + formatSample(field.getJSONObject("sampleValue").toString()) + "\")\n");
                builder.append("\tprivate Map<String, String> " + prettyFieldName + ";\n");

            } else if (isFieldString(field, "sampleValue")) {
                builder.append("\t\tsample = \"" + formatSample(field.getString("sampleValue").toString()) + "\")\n");
                builder.append("\tprivate String " + prettyFieldName + ";\n");

            } else if (isFieldInt(field, "sampleValue")) {
                builder.append("\t\tsample = \"" + field.getInt("sampleValue") + "\")\n");
                builder.append("\tprivate int " + prettyFieldName + ";\n");

            } else if (isFieldBool(field, "sampleValue")) {
                builder.append("\t\tsample = \"" + field.getBoolean("sampleValue") + "\")\n");
                builder.append("\tprivate int " + prettyFieldName + ";\n");

            } else {
                builder.append("\t\tsample = \"" + "\")\n");
                builder.append("\tUnknown Field Type: " + prettyFieldName + "\n");

            }
            //System.out.println("Type: " + sampleObj.getClass().getSimpleName());
            builder.append("\n");

        }

        builder.append("}\n"); // class close curly brace
        System.out.println(builder);
        return builder.toString();

    }

    private String formatSample(String input) {
        //String result = input.replaceAll("\"", "\\\"");
        String result = input.replaceAll("\"", Matcher.quoteReplacement("\\\""));

        return result;
    }

    private boolean isFieldArray(JSONObject obj, String field) {

        try {
            obj.getJSONArray(field);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private boolean isFieldString(JSONObject obj, String field) {
        try {
            obj.getString(field);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private boolean isFieldObject(JSONObject obj, String field) {
        try {
            obj.getJSONObject(field);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private boolean isFieldInt(JSONObject obj, String field) {
        try {
            obj.getInt(field);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private boolean isFieldBool(JSONObject obj, String field) {
        try {
            obj.getBoolean(field);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private String convertToCamelCase(String input, boolean capitalInital) {
        if (input == null) {
            return null;
        }

        if (input.length() == 0) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] scratch = input.toLowerCase().split("[\\s@&.?$+-_]+");

        for (int i = 0; i < scratch.length; i++) {
            String word = scratch[i].trim();
            if (word.length() == 0) {
                continue;
            } else if (!capitalInital && i == 0) {
                result.append(word);
            } else {
                result.append(Character.toUpperCase(word.charAt(0)) + word.substring(1));
            }
        }

        return result.toString();
    }

    public void writeFile(String contents, String directoryName, String fileName) throws IOException {

        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.err.println("Failed to create one or more directories");
            }
        }

        File file = new File(directoryName + "/" + fileName);
        FileUtils.writeStringToFile(file, contents, "UTF-8");
    }

}
