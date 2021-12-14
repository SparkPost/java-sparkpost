
package com.sparkpost.resources;

import java.lang.reflect.Type;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.DeliverabiltyMetricsResponse;
import com.sparkpost.model.responses.Response;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceMetricTests extends BaseResourceTest {

    @BeforeClass
    public static void setUpClass() {
        Configurator.setRootLevel(Level.DEBUG);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private static final class StubResponse extends DeliverabiltyMetricsResponse {

        public static DeliverabiltyMetricsResponse decode(Response response, Type typeOfT) {
            return new StubResponse();
        }
    }

    private StubRestConnection buildStubConnection() {
        StubResponse stubResponse = new StubResponse();
        StubRestConnection conn = new StubRestConnection(stubResponse);
        return conn;
    }

    @Test
    public void testGetDiscoverabilityLinks() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        Response discoverabilityLinks = ResourceMetrics.getDiscoverabilityLinks(conn);
        Assert.assertNotNull(discoverabilityLinks);

        Assert.assertEquals(conn.getRequestUri(), "/metrics/");
        verifyWasGet(conn);
    }

    @Test
    public void testGetDeliverabilityMetricsSummary() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";

        Response discoverabilityLinks = ResourceMetrics.getDeliverabilityMetricsSummary(conn, from, to, domains, campaigns, templates, metrics, timezone);
        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone");
        verifyWasGet(conn);
    }

    @Test
    public void testGetDeliverabilityMetricsByDomain() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";
        String orderBy = "orderBy";

        Response discoverabilityLinks = ResourceMetrics
                .getDeliverabilityMetricsByDomain(conn, from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);
        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/domain?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit&order_by=orderBy");
        verifyWasGet(conn);
    }

    @Test
    public void testGetDeliverabilityMetricsByCampaign() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";
        String orderBy = "orderBy";

        Response discoverabilityLinks = ResourceMetrics
                .getDeliverabilityMetricsByCampaign(conn, from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);
        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/campaign?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit&order_by=orderBy");
        verifyWasGet(conn);
    }

    @Test
    public void testGetDeliverabilityMetricsByTemplate() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";
        String orderBy = "orderBy";

        Response discoverabilityLinks = ResourceMetrics
                .getDeliverabilityMetricsByTemplate(conn, from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);
        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/template?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit&order_by=orderBy");
        verifyWasGet(conn);
    }

    @Test
    public void testGetDeliverabilityMetricsByWatchedDomain() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";
        String orderBy = "orderBy";

        Response discoverabilityLinks = ResourceMetrics
                .getDeliverabilityMetricsByWatchedDomain(conn, from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);
        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/watched-domain?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit&order_by=orderBy");
        verifyWasGet(conn);
    }

    @Test
    public void testGetTimeSeriesMetrics() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String precision = "precision";
        Response discoverabilityLinks = ResourceMetrics.getTimeSeriesMetrics(conn, from, to, domains, campaigns, templates, precision, metrics, timezone);

        Assert.assertNotNull(discoverabilityLinks);

        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/time-series?precision=precision&from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone");
        verifyWasGet(conn);
    }

    @Test
    public void testGetBounceReasonMetrics() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";
        Response discoverabilityLinks = ResourceMetrics.getBounceReasonMetrics(conn, from, to, domains, campaigns, templates, metrics, timezone, limit);
        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/bounce-reason?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testGetBounceReasonMetricsByDomain() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getBounceReasonMetricsByDomain(conn, from, to, domains, campaigns, templates, metrics, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/bounce-reason/domain?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testBounceClassificationMetrics() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String metrics = "metrics";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getBounceClassificationMetrics(conn, from, to, domains, campaigns, templates, metrics, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/bounce-classification?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testRejectionReasonMetrics() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getRejectionReasonMetrics(conn, from, to, domains, campaigns, templates, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/rejection-reason?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testRejectionReasonMetricsByDomain() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getRejectionReasonMetricsByDomain(conn, from, to, domains, campaigns, templates, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/rejection-reason/domain?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testDelayReasonMetrics() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getDelayReasonMetrics(conn, from, to, domains, campaigns, templates, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/delay-reason?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testDelayReasonMetricsByDomain() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getDelayReasonMetricsByDomain(conn, from, to, domains, campaigns, templates, timezone, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/delay-reason/domain?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testEngagementDetails() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";
        String metrics = "metrics";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getEngagementDetails(conn, from, to, timezone, metrics, campaigns, templates, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/link-name?from=from%40domain.com&to=to%40domain.com&campaigns=comapigns&templates=templates&metrics=metrics&timezone=timezone&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testDeliveriesByAttempt() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String from = "from@domain.com";
        String to = "to@domain.com";
        String domains = "domains";
        String campaigns = "comapigns";
        String templates = "templates";
        String timezone = "timezone";

        Response discoverabilityLinks = ResourceMetrics.getDeliveriesByAttempt(conn, from, to, domains, campaigns, templates, timezone);

        Assert.assertNotNull(discoverabilityLinks);
        Assert
                .assertEquals(
                        conn.getRequestUri(),
                        "/metrics/deliverability/attempt?from=from%40domain.com&to=to%40domain.com&domains=domains&campaigns=comapigns&templates=templates&timezone=timezone");
        verifyWasGet(conn);
    }

    @Test
    public void testCampaignsList() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String match = "match";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getCampaignsList(conn, match, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert.assertEquals(conn.getRequestUri(), "/metrics/campaigns?match=match&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testDomainsList() throws SparkPostException {
        StubRestConnection conn = buildStubConnection();
        String match = "match";
        String limit = "limit";

        Response discoverabilityLinks = ResourceMetrics.getDomainsList(conn, match, limit);

        Assert.assertNotNull(discoverabilityLinks);
        Assert.assertEquals(conn.getRequestUri(), "/metrics/domains?match=match&limit=limit");
        verifyWasGet(conn);
    }

}
