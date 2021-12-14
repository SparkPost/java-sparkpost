
package com.sparkpost.model;

import java.net.URISyntaxException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkpost.model.MessageEventsQueryBuilder.BounceClass;
import com.sparkpost.resources.Endpoint;

public class MessageEventsQueryBuilderTest {

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

    @Test
    public void testNoQuerySet() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test", query);

    }

    @Test
    public void testOneBounceClass() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addBounceClass(BounceClass.DNS_FAILURE);

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?bounce_classes=21", query);

    }

    @Test
    public void testTwoBounceClasses() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addBounceClass(BounceClass.INVALID_RECIPIENT);
        queryBuilder.addBounceClass(BounceClass.SOFT_BOUNCE);

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?bounce_classes=10%2C20", query);
    }

    @Test
    public void testOneCampaignId() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addCampaignId("1234");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?campaign_ids=1234", query);

    }

    @Test
    public void testTwoCampaignIds() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addCampaignId("1111");
        queryBuilder.addCampaignId("2222");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?campaign_ids=1111%2C2222", query);
    }

    @Test
    public void testOneFriendlyFrom() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addFriendlyFrom("test1@example.com");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?friendly_froms=test1%40example.com", query);

    }

    @Test
    public void testTwoFriendlyFroms() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addFriendlyFrom("test1@example.com");
        queryBuilder.addFriendlyFrom("test2@example.com");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?friendly_froms=test1%40example.com%2Ctest2%40example.com", query);
    }

    @Test
    public void testOneFromDate() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.setFromDateTime("2014-07-20T08:00");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?from=2014-07-20T08%3A00", query);

    }

    @Test
    public void testOneMessageId() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addMessageId("xxxx");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?message_ids=xxxx", query);

    }

    @Test
    public void testTwoMessageIds() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addMessageId("xxxx");
        queryBuilder.addMessageId("yyyy");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?message_ids=xxxx%2Cyyyy", query);
    }

    @Test
    public void testReason() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.setReason("bounce");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?reason=bounce", query);

    }

    @Test
    public void testOneRecipient() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addRecipient("test1@example.com");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?recipients=test1%40example.com", query);

    }

    @Test
    public void testTwoRecipients() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addRecipient("test1@example.com");
        queryBuilder.addRecipient("test2@example.com");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?recipients=test1%40example.com%2Ctest2%40example.com", query);
    }

    @Test
    public void testOneSubaccount() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addSubAccount("101");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?subaccounts=101", query);

    }

    @Test
    public void testTwoSubaccounts() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addSubAccount("101");
        queryBuilder.addSubAccount("102");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?subaccounts=101%2C102", query);
    }

    @Test
    public void testOneTempateId() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addTemplateId("101");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?template_ids=101", query);

    }

    @Test
    public void testTwoTemplateIds() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addTemplateId("101");
        queryBuilder.addTemplateId("102");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?template_ids=101%2C102", query);
    }

    @Test
    public void testOneTransmissionId() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addTransmissionId("101");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        Assert.assertEquals("/test?transmission_ids=101", query);

    }

    @Test
    public void testTwoTransmissionIds() throws URISyntaxException {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();
        queryBuilder.setSortOutput(true);

        queryBuilder.addTransmissionId("101");
        queryBuilder.addTransmissionId("102");

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();

        Assert.assertNotNull(query);
        Assert.assertEquals("/test?transmission_ids=101%2C102", query);
    }

}
