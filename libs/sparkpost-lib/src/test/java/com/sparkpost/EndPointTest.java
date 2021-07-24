
package com.sparkpost;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkpost.resources.Endpoint;

public class EndPointTest {

    public EndPointTest() {
    }

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

    /**
     * Test of "/" is added to empty Endoint
     */
    @Test
    public void testSimpleEmptyEndPoint() {
        Endpoint endPoint = new Endpoint("");

        String result = endPoint.toString();
        Assert.assertEquals("/", result);
    }

    /**
     * Test if "/" URI starts with only one `/`
     */
    @Test
    public void testSimpleEmptyEndPoint2() {
        Endpoint endPoint = new Endpoint("/");

        String result = endPoint.toString();
        Assert.assertEquals("/", result);
    }

    /**
     * Test if "/" URI starts with only one `/`
     */
    @Test
    public void testSimpleEndPointLeadingSlash() {
        Endpoint endPoint = new Endpoint("/test");

        String result = endPoint.toString();
        Assert.assertEquals("/test", result);
    }

    /**
     * Test of Simple EndPoint
     */
    @Test
    public void testSimpleEndPoint() {
        Endpoint endPoint = new Endpoint("transmissions");
        endPoint.addParam("num_rcpt_errors", 3);

        String result = endPoint.toString();
        Assert.assertEquals("/transmissions?num_rcpt_errors=3", result);
    }

    /**
     * Test of EndPoint that contains boolean
     */
    @Test
    public void testEndPointWithBoolean() {
        Endpoint endPoint = new Endpoint("transmissions");
        endPoint.addParam("myBool", new Boolean(true));

        String result = endPoint.toString();
        Assert.assertEquals("/transmissions?myBool=true", result);
    }

    /**
     * Test of EndPoint that contains boolean
     */
    @Test
    public void testEndPointWithInteger() {
        Endpoint endPoint = new Endpoint("transmissions");
        endPoint.addParam("myInteger", new Integer(100));

        String result = endPoint.toString();
        Assert.assertEquals("/transmissions?myInteger=100", result);
    }

    /**
     * Test of Simple EndPoint
     */
    @Test
    public void testEndPointWithMultipleParameters() {
        Endpoint endPoint = new Endpoint("transmissions");
        endPoint.addParam("num_rcpt_errors", 3);
        endPoint.addParam("myBool", new Boolean(false));
        endPoint.addParam("MyInteger", new Integer(0));

        String result = endPoint.toString();
        Assert.assertEquals("/transmissions?num_rcpt_errors=3&myBool=false&MyInteger=0", result);
    }

}
