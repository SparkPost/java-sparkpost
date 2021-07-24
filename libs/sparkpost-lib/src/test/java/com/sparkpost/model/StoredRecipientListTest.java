
package com.sparkpost.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class StoredRecipientListTest {

    private String STORED_RECIPIENT_LIST_JSON = "{ \"list_id\":\"123412341234\" }";

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
     *
     */
    @Test
    public void testDecodeStoredRecipientList() {
        Gson gson = new Gson();
        StoredRecipientList storedRecipientList = gson.fromJson(this.STORED_RECIPIENT_LIST_JSON, StoredRecipientList.class);
        Assert.assertNotNull(storedRecipientList);

        Assert.assertEquals(storedRecipientList.getListId(), "123412341234");
    }

    /**
     *
     */
    @Test
    public void testStoredRecipientListRoundtrip() {
        Gson gson = new Gson();
        StoredRecipientList storedRecipientList = gson.fromJson(this.STORED_RECIPIENT_LIST_JSON, StoredRecipientList.class);
        Assert.assertNotNull(storedRecipientList);

        String storedReciplist_json = storedRecipientList.toJson();
        StoredRecipientList storedRecipientList2 = gson.fromJson(storedReciplist_json, StoredRecipientList.class);

        Assert.assertEquals(storedRecipientList.getListId(), storedRecipientList2.getListId());
    }

}
