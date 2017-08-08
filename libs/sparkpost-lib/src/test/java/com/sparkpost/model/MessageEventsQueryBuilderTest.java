
package com.sparkpost.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
        Logger.getRootLogger().setLevel(Level.DEBUG);
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
    public void testTwoBounceClass() {
        MessageEventsQueryBuilder queryBuilder = new MessageEventsQueryBuilder();

        queryBuilder.addBounceClass(BounceClass.INVALID_RECIPIENT);
        queryBuilder.addBounceClass(BounceClass.SOFT_BOUNCE);

        Endpoint ep = new Endpoint("test");
        queryBuilder.buildQuery(ep);

        String query = ep.toString();
        Assert.assertNotNull(query);
        //Assert.assertEquals("/test?bounce_classes=20%2C10", query);

    }

}
