
package com.sparkpost.resources;

import org.junit.Assert;

import com.sparkpost.testhelpers.StubRestConnection;

public class BaseResourceTest {

    protected void verifyWasGet(StubRestConnection conn) {
        Assert.assertTrue(conn.wasGet());
        Assert.assertFalse(conn.wasDelete());
        Assert.assertFalse(conn.wasPut());
        Assert.assertFalse(conn.wasPost());
    }

    protected void verifyWasPut(StubRestConnection conn) {
        Assert.assertFalse(conn.wasGet());
        Assert.assertFalse(conn.wasDelete());
        Assert.assertTrue(conn.wasPut());
        Assert.assertFalse(conn.wasPost());
    }

    protected void verifyWasPost(StubRestConnection conn) {
        Assert.assertFalse(conn.wasGet());
        Assert.assertFalse(conn.wasDelete());
        Assert.assertFalse(conn.wasPut());
        Assert.assertTrue(conn.wasPost());
    }

    protected void verifyWasDelete(StubRestConnection conn) {
        Assert.assertFalse(conn.wasGet());
        Assert.assertTrue(conn.wasDelete());
        Assert.assertFalse(conn.wasPut());
        Assert.assertFalse(conn.wasPost());
    }

}
