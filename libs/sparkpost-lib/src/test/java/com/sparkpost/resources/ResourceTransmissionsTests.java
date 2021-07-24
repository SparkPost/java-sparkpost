
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
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.TransmissionWithRecipientList;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TransmissionCreateResponse;
import com.sparkpost.model.responses.TransmissionListResponse;
import com.sparkpost.model.responses.TransmissionRetrieveResults;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceTransmissionsTests extends BaseResourceTest {

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

    private static final class StubTransmissionCreateResponse extends TransmissionCreateResponse {

        public static StubTransmissionCreateResponse decode(Response response, Type typeOfT) {
            return new StubTransmissionCreateResponse();
        }
    }

    private static final class StubTransmissionRetrieveResults extends TransmissionRetrieveResults {

        public static StubTransmissionRetrieveResults decode(Response response, Type typeOfT) {
            return new StubTransmissionRetrieveResults();
        }
    }

    private static final class StubTransmissionListResponse extends TransmissionListResponse {

        public static StubTransmissionListResponse decode(Response response, Type typeOfT) {
            return new StubTransmissionListResponse();
        }
    }

    private StubRestConnection buildStubConnection(Response response) {
        StubRestConnection conn = new StubRestConnection(response);
        return conn;
    }

    @Test
    public void testCreate1() throws SparkPostException {
        Integer numRcptErrors = 0;
        TransmissionWithRecipientArray trans = new TransmissionWithRecipientArray();

        StubRestConnection conn = buildStubConnection(new StubTransmissionCreateResponse());
        Response response = ResourceTransmissions.create(conn, numRcptErrors, trans);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/transmissions?num_rcpt_errors=0");
        verifyWasPost(conn);
    }

    @Test
    public void testCreate2() throws SparkPostException {
        Integer numRcptErrors = 0;
        TransmissionWithRecipientList trans = new TransmissionWithRecipientList();

        StubRestConnection conn = buildStubConnection(new StubTransmissionCreateResponse());
        Response response = ResourceTransmissions.create(conn, numRcptErrors, trans);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/transmissions?num_rcpt_errors=0");
        verifyWasPost(conn);
    }

    @Test
    public void testRetrieve() throws SparkPostException {
        String id = "id";

        StubRestConnection conn = buildStubConnection(new StubTransmissionRetrieveResults());
        Response response = ResourceTransmissions.retrieve(conn, id);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/transmissions/" + id);
        verifyWasGet(conn);
    }

    @Test
    public void testList() throws SparkPostException {
        String campaignId = "campaignId";
        String templateId = "templateId";

        StubRestConnection conn = buildStubConnection(new StubTransmissionListResponse());
        Response response = ResourceTransmissions.list(conn, campaignId, templateId);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/transmissions?campaign_id=campaignId&template_id=templateId");
        verifyWasGet(conn);
    }

}
