
package com.sparkpost.samples.webhooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sparkpost.model.webhook.event.AbstractWebhookEvent;
import com.sparkpost.model.webhook.event.BounceEvent;
import com.sparkpost.model.webhook.event.ClickEvent;
import com.sparkpost.model.webhook.event.DelayEvent;
import com.sparkpost.model.webhook.event.DeliveryEvent;
import com.sparkpost.model.webhook.event.GenerationFailureEvent;
import com.sparkpost.model.webhook.event.GenerationRejectionEvent;
import com.sparkpost.model.webhook.event.InjectionEvent;
import com.sparkpost.model.webhook.event.LinkUnsubscribeEvent;
import com.sparkpost.model.webhook.event.ListUnsubscribeEvent;
import com.sparkpost.model.webhook.event.OpenEvent;
import com.sparkpost.model.webhook.event.OutOfBandEvent;
import com.sparkpost.model.webhook.event.PolicyRejectionEvent;
import com.sparkpost.model.webhook.event.RelayDeliveryEvent;
import com.sparkpost.model.webhook.event.RelayInjectionEvent;
import com.sparkpost.model.webhook.event.RelayPermanentFailureEvent;
import com.sparkpost.model.webhook.event.RelayRejectionEvent;
import com.sparkpost.model.webhook.event.RelayTemporaryFailureEvent;
import com.sparkpost.model.webhook.event.SmsStatusEvent;
import com.sparkpost.model.webhook.event.SpamComplaintEvent;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;

/**
 * NOTE: Webhook events are experimental code and are subject to change.
 */
@SuppressWarnings("rawtypes")
public class WebhookDecoderSample extends SparkPostBaseApp {

    private final static Map<String, Class> classMap;
    //private static final Gson GSON = new Gson();

    public WebhookDecoderSample() {

    }

    public static void main(String[] args) {

        WebhookDecoderSample decoder = new WebhookDecoderSample();
        String json = SparkPostBaseApp.loadJsonFile("webhook_samples.json");

        List<AbstractWebhookEvent> events = decoder.decodeEvents(json);

        System.out.println("Found " + events.size() + " events:");

        for (AbstractWebhookEvent event : events) {
            System.out.println("\t " + event.getType() + " - " + event.getClass().getSimpleName());
        }

    }

    public List<AbstractWebhookEvent> decodeEvents(String json) {
        // Use your preferred means to translate a JSON array to a list o typed object.
        //
        // Here, this demo code unfortunately decodes each event twice so it can
        // pick the type out and create a concrete representation of each event

        JSONArray rawEventArray = new JSONArray(json);

        List<AbstractWebhookEvent> webhookEvents = new ArrayList<AbstractWebhookEvent>();
        for (int i = 0; i < rawEventArray.length(); i++) {
            JSONObject jsonobject = rawEventArray.getJSONObject(i);
            JSONObject eventWrapper = jsonobject.getJSONObject("msys");

            Object[] keys = eventWrapper.keySet().toArray();
            if (keys.length != 1) {
                System.err.println("ERROR: there should only be one key in the event keyset. [" + eventWrapper + "]");
                continue;
            }
            String classGroup = keys[0].toString();
            JSONObject eventData = eventWrapper.getJSONObject(classGroup);
            String type = eventData.getString("type");

            String classId = classGroup + "." + type;
            Class c = classMap.get(classId);
            if (c == null) {
                System.err.println("Unknow event type: " + classId + "\n[" + eventData + "]");
                continue;
            }

            //AbstractWebhookEvent eventObj = GSON.fromJson(eventData.toString(), c);
            //webhookEvents.add(eventObj);

        }

        return webhookEvents;
    }

    static {
        classMap = new HashMap<String, Class>();
        classMap.put("message_event.bounce", BounceEvent.class);
        classMap.put("message_event.delivery", DeliveryEvent.class);
        classMap.put("message_event.injection", InjectionEvent.class);
        classMap.put("message_event.sms_status", SmsStatusEvent.class);
        classMap.put("message_event.spam_complaint", SpamComplaintEvent.class);
        classMap.put("message_event.out_of_band", OutOfBandEvent.class);
        classMap.put("message_event.policy_rejection", PolicyRejectionEvent.class);
        classMap.put("message_event.delay", DelayEvent.class);

        classMap.put("track_event.click", ClickEvent.class);
        classMap.put("track_event.open", OpenEvent.class);

        classMap.put("unsubscribe_event.list_unsubscribe", ListUnsubscribeEvent.class);
        classMap.put("unsubscribe_event.link_unsubscribe", LinkUnsubscribeEvent.class);

        classMap.put("gen_event.generation_failure", GenerationFailureEvent.class);
        classMap.put("gen_event.generation_rejection", GenerationRejectionEvent.class);

        classMap.put("relay_event.relay_injection", RelayInjectionEvent.class);
        classMap.put("relay_event.relay_rejection", RelayRejectionEvent.class);
        classMap.put("relay_event.relay_delivery", RelayDeliveryEvent.class);
        classMap.put("relay_event.relay_tempfail", RelayTemporaryFailureEvent.class);
        classMap.put("relay_event.relay_permfail", RelayPermanentFailureEvent.class);

    }
}
