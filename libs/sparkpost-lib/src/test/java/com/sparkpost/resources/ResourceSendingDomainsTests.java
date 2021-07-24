
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
import com.sparkpost.model.SendingDomain;
import com.sparkpost.model.VerifyAttributes;
import com.sparkpost.model.responses.Response;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceSendingDomainsTests extends BaseResourceTest {

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

    private static final class StubResponse extends Response {

        public static Response decode(Response response, Type typeOfT) {
            return new StubResponse();
        }
    }

    private StubRestConnection buildStubConnection() {
        StubRestConnection conn = new StubRestConnection(new StubResponse());
        return conn;
    }

    @Test
    public void testCreate() throws SparkPostException {
        SendingDomain sendingDomain = new SendingDomain();

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSendingDomains.create(conn, sendingDomain);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/sending-domains");
        verifyWasPost(conn);
    }

    @Test
    public void testRetrieve() throws SparkPostException {
        String domainName = "domainName";

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSendingDomains.retrieve(conn, domainName);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/sending-domains/" + domainName);
        verifyWasGet(conn);
    }

    @Test
    public void testUpdate() throws SparkPostException {
        String domainName = "domainName";
        SendingDomain sendingDomain = new SendingDomain();

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSendingDomains.update(conn, domainName, sendingDomain);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/sending-domains/" + domainName);
        verifyWasPut(conn);
    }

    @Test
    public void testVerify() throws SparkPostException {
        String domainName = "domainName";
        VerifyAttributes verifyAttributes = new VerifyAttributes();

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSendingDomains.verify(conn, domainName, verifyAttributes);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/sending-domains/" + domainName + "/verify");
        verifyWasPost(conn);
    }

}
