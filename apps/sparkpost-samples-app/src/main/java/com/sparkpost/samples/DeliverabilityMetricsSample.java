
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.MetricsBounceFields;
import com.sparkpost.model.MetricsDelayReasonFields;
import com.sparkpost.model.MetricsDeliveriesFields;
import com.sparkpost.model.MetricsEngagementFields;
import com.sparkpost.model.MetricsFields;
import com.sparkpost.model.MetricsRejectFields;
import com.sparkpost.model.responses.CampaignListResponse;
import com.sparkpost.model.responses.DeliverabiltyMetricsResponse;
import com.sparkpost.model.responses.DomainListResponse;
import com.sparkpost.model.responses.MetricLinkResponse;
import com.sparkpost.resources.ResourceMetrics;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Note: This code has a lot of redundancy so it is easy to copy and paste into your own code base as a starting point.
 * Also, This code does not do any of the error checking that should be done in production code.
 */
public class DeliverabilityMetricsSample extends SparkPostBaseApp {

    private static final String FROM_DATE = "2014-07-11T08:00";

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        if (args.length == 0) {
            System.err.println("You must pass the metrics you want to querying");
            System.err.println("commands\n");

            System.err.println("Deliverability");
            System.err.println("\tdeliverability");
            System.err.println("\tdeliverability-domain");
            System.err.println("\tdeliverability-campaign");
            System.err.println("\tdeliverability-template");
            System.err.println("\tdeliverability-watched-domain");

            System.err.println("Time Series");
            System.err.println("\ttime-series-day");
            System.err.println("\ttime-series-month");

            System.err.println("Bounce Reasons");
            System.err.println("\tbounce-reason");
            System.err.println("\tbounce-reason-domain");
            System.err.println("\tbounce-reason-classification");
            System.err.println("\tbounce-reason-classification");

            System.err.println("Rejections");
            System.err.println("\trejection");
            System.err.println("\trejection-domain");

            System.err.println("Delays");
            System.err.println("\tdelay");
            System.err.println("\tdelay-domain");

            System.err.println("Others");
            System.err.println("\tengagement");
            System.err.println("\tdeliveries");
            System.err.println("\tcampaign-list");
            System.err.println("\tdomain-list");

            return;
        }

        for (String command : args) {
            long start = System.currentTimeMillis();
            switch (command) {

                case "deliverability": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityMetric();
                }
                    break;
                case "deliverability-domain": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityDomainMetric();
                }
                    break;
                case "deliverability-campaign": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityCampaignMetric();
                }
                    break;
                case "deliverability-template": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityTemplateMetric();
                }
                    break;
                case "deliverability-watched-domain": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityWatchedDomainMetric();
                }
                    break;
                case "time-series-month": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityTimeSeriesMetrics("month");
                }
                    break;
                case "time-series-day": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityTimeSeriesMetrics("day");
                }
                    break;
                case "bounce-reason": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityBounceReasonMetrics();
                }
                    break;
                case "bounce-reason-domain": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityBounceReasonByDomainMetrics();
                }
                    break;
                case "bounce-reason-classification": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityBounceReasonByClassificationMetrics();
                }
                    break;
                case "rejection": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityRejectionMetrics();
                }
                    break;
                case "rejection-domain": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityRejectionDomainMetrics();
                }
                    break;
                case "delay": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityDelayMetrics();
                }
                    break;
                case "delay-domain": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityDelayDomainMetrics();
                }
                    break;
                case "engagement": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityEngagementMetrics();
                }
                    break;
                case "deliveries": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityDeliveriesMetrics();
                }
                    break;
                case "campaign-list": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityCampaignListMetrics();
                }
                    break;
                case "domain-list": {
                    logger.debug(command);
                    DeliverabilityMetricsSample sample = new DeliverabilityMetricsSample();
                    sample.deliverabilityDomainListMetrics();
                }
                    break;
                default:
                    System.err.println("Error unknown command: :\"" + command + "\"");
                    break;

            }
            logger.debug("duration: " + (System.currentTimeMillis() - start) + " mSec");
        }
        logger.debug("done");
    }

    /**
     * Provides high-level summary of aggregate metrics and lists the child endpoints that contain aggregate data, which can be used as "group by" qualifiers.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/deliverability-metrics/deliverability-metrics-summary
     */
    private void deliverabilityMetric() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDeliverabilityMetricsSummary(connection, from, to, domains, campaigns, templates, metrics, timezone);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "");
        }

        logger.debug("-------------- LINKS -------------");
        List<MetricLinkResponse> links = response.getLinks();
        for (MetricLinkResponse link : links) {
            logger.debug("Link: " + link.getRel());
        }
    }

    /**
     * Provides aggregate metrics grouped by domain over the time window specified.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/discoverability-links/deliverability-metrics-by-domain
     */
    private void deliverabilityDomainMetric() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;
        String orderBy = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDeliverabilityMetricsByDomain(connection, from, to, domains, campaigns, templates, metrics, timezone, orderBy, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Domain: \"" + m.get(MetricsFields.DOMAIN) + "\"");
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides aggregate metrics grouped by campaign over the time window specified.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/deliverability-metrics-by-binding/deliverability-metrics-by-campaign
     */
    private void deliverabilityCampaignMetric() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;
        String orderBy = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDeliverabilityMetricsByCampaign(connection, from, to, domains, campaigns, templates, metrics, timezone, orderBy, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Campaing ID: \"" + m.get(MetricsFields.CAMPAIGN_ID) + "\"");
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides aggregate metrics grouped by template over the time window specified.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/deliverability-metrics-by-binding-group/deliverability-metrics-by-template
     */
    private void deliverabilityTemplateMetric() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;
        String orderBy = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDeliverabilityMetricsByTemplate(connection, from, to, domains, campaigns, templates, metrics, timezone, orderBy, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Template ID: \"" + m.get(MetricsFields.TEMPLATE_ID) + "\"");
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides aggregate metrics grouped by watched domain over the time window specified. The difference between domain and watched domain is that watched
     * domains are comprised of the top 99% domains in the world.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/deliverability-metrics-by-binding-group/deliverability-metrics-by-watched-domain
     */
    private void deliverabilityWatchedDomainMetric() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;
        String orderBy = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDeliverabilityMetricsByWatchedDomain(connection, from, to, domains, campaigns, templates, metrics, timezone, orderBy, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Template ID: \"" + m.get(MetricsFields.WATCHED_DOMAIN) + "\"");
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics ordered by a precision of time.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/time-series/time-series-metrics
     */
    private void deliverabilityTimeSeriesMetrics(String precision) throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsFields.ALL_FIELDS;
        String timezone = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getTimeSeriesMetrics(connection, from, to, domains, campaigns, templates, precision, metrics, timezone);

        //logger.debug("Response: " + response.getResponseBody());
        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Template ID: \"" + m.get(MetricsFields.TIMESTAMP) + "\"");
            dumpMetric(m, MetricsFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to bounce events, grouped by the bounce reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/time-series/bounce-reason-metrics
     */
    private void deliverabilityBounceReasonMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsBounceFields.ALL_FIELDS;
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getBounceReasonMetrics(connection, from, to, domains, campaigns, templates, metrics, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Reason: \"" + m.get(MetricsBounceFields.REASON) + "\"");
            dumpMetric(m, MetricsBounceFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to bounce events, grouped by the domain and bounce reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/bounce-reasons-by-domain/bounce-reason-metrics-by-domain
     */
    private void deliverabilityBounceReasonByDomainMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsBounceFields.ALL_FIELDS;
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getBounceReasonMetricsByDomain(connection, from, to, domains, campaigns, templates, metrics, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Reason: \"" + m.get(MetricsBounceFields.REASON) + "\"");
            dumpMetric(m, MetricsBounceFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to bounce events, grouped by the bounce classification.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/bounce-reasons/bounce-classification-metrics
     */
    private void deliverabilityBounceReasonByClassificationMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String metrics = MetricsBounceFields.ALL_FIELDS;
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getBounceClassificationMetrics(connection, from, to, domains, campaigns, templates, metrics, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Description: \"" + m.get(MetricsBounceFields.BOUNCE_CLASS_DESCRIPTION) + "\"");
            dumpMetric(m, MetricsBounceFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to rejection events, grouped by the rejection reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/rejection-reasons/rejection-reason-metrics
     */
    private void deliverabilityRejectionMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics.getRejectionReasonMetrics(connection, from, to, domains, campaigns, templates, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Description: \"" + m.get(MetricsRejectFields.REASON) + "\"");
            dumpMetric(m, MetricsRejectFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to rejection events, grouped by the domain and rejection reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/bounce-classifications/rejection-reason-metrics-by-domain
     */
    private void deliverabilityRejectionDomainMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getRejectionReasonMetricsByDomain(connection, from, to, domains, campaigns, templates, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Rejection Category: \"" + m.get(MetricsRejectFields.REJECTION_CATEGORY_NAME) + "\"");
            dumpMetric(m, MetricsRejectFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to delay events, grouped by the delay reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/rejection-reasons-by-domain/delay-reason-metrics
     */
    private void deliverabilityDelayMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics.getDelayReasonMetrics(connection, from, to, domains, campaigns, templates, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Delay Reason: \"" + m.get(MetricsDelayReasonFields.REASON) + "\"");
            dumpMetric(m, MetricsDelayReasonFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to delay events, grouped by the domain and delay reasons.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/delay-reasons-by-domain/delay-reason-metrics-by-domain
     */
    private void deliverabilityDelayDomainMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String timezone = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics
                .getDelayReasonMetricsByDomain(connection, from, to, domains, campaigns, templates, timezone, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Delay Reason: \"" + m.get(MetricsDelayReasonFields.REASON) + "\"");
            dumpMetric(m, MetricsDelayReasonFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides deliverability metrics, specific to engagement events (clicks/opens), grouped by the link name (or URL if no link name exists).
     * See: https://developers.sparkpost.com/api/#/reference/metrics/engagement-details/engagement-details
     */
    private void deliverabilityEngagementMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String campaigns = null;
        String templates = null;

        // list of metrics we are interested in
        String timezone = null;
        String limit = null;
        String metrics = MetricsEngagementFields.ALL_FIELDS;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics.getEngagementDetails(connection, from, to, timezone, metrics, campaigns, templates, limit);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Linke Name: \"" + m.get(MetricsEngagementFields.LINK_NAME) + "\"");
            dumpMetric(m, MetricsEngagementFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    /**
     * Provides aggregate count of deliveries grouped by the attempt number.
     * See: https://developers.sparkpost.com/api/#/reference/metrics/deliveries-by-attempt
     */
    private void deliverabilityDeliveriesMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String from = FROM_DATE;
        String to = null;
        String domains = null;
        String campaigns = null;
        String templates = null;
        String timezone = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DeliverabiltyMetricsResponse response = ResourceMetrics.getDeliveriesByAttempt(connection, from, to, domains, campaigns, templates, timezone);

        List<Map<String, Object>> results = response.getResults();

        logger.debug("-------------- METRICS -------------");
        for (Map<String, Object> m : results) {
            logger.debug("Deliver Attempt");
            dumpMetric(m, MetricsDeliveriesFields.ALL_FIELDS_ARRAY, "\t");
        }
    }

    private void deliverabilityCampaignListMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String match = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        CampaignListResponse response = ResourceMetrics.getCampaignsList(connection, match, limit);

        List<String> results = response.getCampaignList();

        logger.debug("-------------- Campaigns -------------");
        for (String campaign : results) {
            logger.debug("\t" + campaign);
        }
    }

    private void deliverabilityDomainListMetrics() throws SparkPostException, IOException {
        Client client = this.newConfiguredClient();

        String match = null;
        String limit = null;

        IRestConnection connection = new RestConnection(client, getEndPoint());
        DomainListResponse response = ResourceMetrics.getDomainsList(connection, match, limit);

        List<String> results = response.getDomainList();

        logger.debug("-------------- Domains -------------");
        for (String domain : results) {
            logger.debug("\t" + domain);
        }
    }

    /**
     * Internal helper for dumping metrics results
     *
     * @param metricData
     *            the result to dump
     */
    private void dumpMetric(Map<String, Object> metricData, String[] fieldsToDump, String prepend) {
        for (String field : fieldsToDump) {
            Object value = metricData.get(field);
            if (value != null) {
                logger.debug(prepend + field + " = " + value + " (" + value.getClass().getSimpleName() + ")");
            }

        }
    }

}
