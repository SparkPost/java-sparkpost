
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
import com.sparkpost.model.SuppressionList;
import com.sparkpost.model.SuppressionListEntry;
import com.sparkpost.model.responses.Response;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceSuppressionListTests extends BaseResourceTest {

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
    public void testInsertOrUpdate() throws SparkPostException {
        String email = "email";
        SuppressionListEntry entry = new SuppressionListEntry();

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSuppressionList.insertOrUpdate(conn, email, entry);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/suppression-list/" + email);
        verifyWasPut(conn);
    }

    @Test
    public void testInsertOrUpdateBulk() throws SparkPostException {
        SuppressionList suppressionList = new SuppressionList();

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSuppressionList.insertOrUpdateBulk(conn, suppressionList);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/suppression-list/");
        verifyWasPut(conn);
    }

    @Test
    public void testSearch() throws SparkPostException {
        String to = "to";
        String from = "from";
        String types = "types";
        String limit = "limit";

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSuppressionList.search(conn, to, from, types, limit);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/suppression-list?to=to&from=from&types=types&limit=limit");
        verifyWasGet(conn);
    }

    @Test
    public void testCheck() throws SparkPostException {
        String email = "email";

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSuppressionList.check(conn, email);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/suppression-list/" + email);
        verifyWasGet(conn);
    }

    @Test
    public void testRemove() throws SparkPostException {
        String email = "email";

        StubRestConnection conn = buildStubConnection();
        Response response = ResourceSuppressionList.remove(conn, email);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/suppression-list/" + email);
        verifyWasDelete(conn);
    }

}
