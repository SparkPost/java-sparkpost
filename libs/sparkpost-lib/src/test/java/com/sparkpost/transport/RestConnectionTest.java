
package com.sparkpost.transport;

import org.junit.Assert;
import org.junit.Test;

public class RestConnectionTest {

    @Test
    public void constructWithNullBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, null);
        Assert.assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test
    public void constructWithEmptyBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "");
        Assert.assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test(expected = IllegalStateException.class)
    public void constructWithInvalidBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "/");
        Assert.assertEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
    }

    @Test
    public void constructWithValidBaseURL() throws Exception {
        RestConnection restConnection = new RestConnection(null, "http://foo/bar");
        Assert.assertNotEquals(IRestConnection.defaultApiEndpoint, restConnection.getBaseUrl());
        Assert.assertEquals("http://foo/bar", restConnection.getBaseUrl());
    }

}
