
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
import com.sparkpost.model.RecipientList;
import com.sparkpost.model.responses.RecipientListRetrieveResponse;
import com.sparkpost.model.responses.RecipientListsListAllResponse;
import com.sparkpost.model.responses.Response;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceRecipientListsTests extends BaseResourceTest {

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

    private static final class StubRecipientListRetrieveResponse extends RecipientListRetrieveResponse {

        public static RecipientListRetrieveResponse decode(Response response, Type typeOfT) {
            return new StubRecipientListRetrieveResponse();
        }
    }

    private static final class StubRecipientListsListAllResponse extends RecipientListsListAllResponse {

        public static RecipientListsListAllResponse decode(Response response, Type typeOfT) {
            return new StubRecipientListsListAllResponse();
        }
    }

    private StubRestConnection buildStubConnection(Response response) {
        StubRestConnection conn = new StubRestConnection(response);
        return conn;
    }

    @Test
    public void testCreate() throws SparkPostException {
        Integer maxNumberOfRecipientErrors = 0;
        RecipientList recipientList = new RecipientList();

        StubRestConnection conn = buildStubConnection(new StubResponse());
        Response response = ResourceRecipientLists.create(conn, maxNumberOfRecipientErrors, recipientList);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/recipient-lists?num_rcpt_errors=0");
        verifyWasPost(conn);
    }

    @Test
    public void testRetrieve() throws SparkPostException {
        String recipientListId = "recipientListId";

        StubRestConnection conn = buildStubConnection(new StubRecipientListRetrieveResponse());
        Response response = ResourceRecipientLists.retrieve(conn, recipientListId, Boolean.TRUE);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/recipient-lists/recipientListId?show_recipients=true");
        verifyWasGet(conn);
    }

    @Test
    public void testListAll() throws SparkPostException {
        StubRestConnection conn = buildStubConnection(new StubRecipientListsListAllResponse());
        Response response = ResourceRecipientLists.listAll(conn);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/recipient-lists");
        verifyWasGet(conn);
    }

    @Test
    public void testDelete() throws SparkPostException {
        String recipientListId = "recipientListId";

        StubRestConnection conn = buildStubConnection(new StubRecipientListsListAllResponse());
        Response response = ResourceRecipientLists.delete(conn, recipientListId);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/recipient-lists/recipientListId");
        verifyWasDelete(conn);
    }

}
