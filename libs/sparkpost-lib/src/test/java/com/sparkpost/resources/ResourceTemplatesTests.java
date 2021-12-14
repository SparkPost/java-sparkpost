
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
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateCreateResponse;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.model.responses.TemplatePreviewResponse;
import com.sparkpost.model.responses.TemplateRetrieveResponse;
import com.sparkpost.testhelpers.StubRestConnection;

public class ResourceTemplatesTests extends BaseResourceTest {

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

    private static final class StubTemplateCreateResponse extends TemplateCreateResponse {

        public static StubTemplateCreateResponse decode(Response response, Type typeOfT) {
            return new StubTemplateCreateResponse();
        }
    }

    private static final class StubTemplateListResponse extends TemplateListResponse {

        public static StubTemplateListResponse decode(Response response, Type typeOfT) {
            return new StubTemplateListResponse();
        }
    }

    private static final class StubTemplatePreviewResponse extends TemplatePreviewResponse {

        public static StubTemplatePreviewResponse decode(Response response, Type typeOfT) {
            return new StubTemplatePreviewResponse();
        }
    }

    private static final class StubStubTemplateListResponse extends TemplateRetrieveResponse {

        public static TemplateRetrieveResponse decode(Response response, Type typeOfT) {
            return new TemplateRetrieveResponse();
        }
    }

    private StubRestConnection buildStubConnection(Response response) {
        StubRestConnection conn = new StubRestConnection(response);
        return conn;
    }

    @Test
    public void testInsertOrUpdate() throws SparkPostException {
        TemplateAttributes tpl = new TemplateAttributes();

        StubRestConnection conn = buildStubConnection(new StubTemplateCreateResponse());
        Response response = ResourceTemplates.create(conn, tpl);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/templates");
        verifyWasPost(conn);
    }

    @Test
    public void testRetrieve() throws SparkPostException {
        String id = "id";
        Boolean draft = Boolean.TRUE;

        StubRestConnection conn = buildStubConnection(new StubStubTemplateListResponse());
        Response response = ResourceTemplates.retrieve(conn, id, draft);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/templates/id?draft=true");
        verifyWasGet(conn);
    }

    @Test
    public void testListAll() throws SparkPostException {

        StubRestConnection conn = buildStubConnection(new StubTemplateListResponse());
        Response response = ResourceTemplates.listAll(conn);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/templates/");
        verifyWasGet(conn);
    }

    @Test
    public void tesUpdate() throws SparkPostException {
        String id = "id";
        Boolean updatePublished = Boolean.TRUE;
        TemplateAttributes tpl = new TemplateAttributes();

        StubRestConnection conn = buildStubConnection(new StubResponse());
        Response response = ResourceTemplates.update(conn, id, updatePublished, tpl);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/templates/id?update_published=true");
        verifyWasPut(conn);
    }

    @Test
    public void tesPreview() throws SparkPostException {
        String id = "id";
        Boolean draft = Boolean.TRUE;
        TemplateSubstitutionData subst = new TemplateSubstitutionData();

        StubRestConnection conn = buildStubConnection(new StubTemplatePreviewResponse());
        Response response = ResourceTemplates.preview(conn, id, draft, subst);
        Assert.assertNotNull(response);

        Assert.assertEquals(conn.getRequestUri(), "/templates/" + id + "/preview?draft=true");
        verifyWasPost(conn);
    }

    @Test
    public void tesDelete() throws SparkPostException {
        String id = "id";

        StubRestConnection conn = buildStubConnection(new StubResponse());
        Response response = ResourceTemplates.delete(conn, id);
        Assert.assertNotNull(response);

        System.out.println(conn.getRequestUri());
        Assert.assertEquals(conn.getRequestUri(), "/templates/" + id);
        verifyWasDelete(conn);
    }

}
