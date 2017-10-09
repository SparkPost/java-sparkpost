package com.sparkpost.transport;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RestConnectionTest {

    @Test
    public void constructWithNullBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, null);
        assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test
    public void constructWithEmptyBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "");
        assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test(expected = IllegalStateException.class)
    public void constructWithInvalidBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "/");
        assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test
    public void constructWithValidBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "http://foo/bar");
        assertNotEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
        assertEquals("http://foo/bar", restConnection.getBaseUrl());
    }

}
