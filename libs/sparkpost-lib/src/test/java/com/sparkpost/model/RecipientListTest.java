
package com.sparkpost.model;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class RecipientListTest {

    private String RECIPIENT_LIST_JSON = "{\n"
            + "  \"id\": \"unique_id_4_graduate_students_list\",\n"
            + "  \"name\": \"graduate_students\",\n"
            + "  \"description\": \"An email list of graduate students at UMBC\",\n"
            + "  \"attributes\": {\n"
            + "    \"internal_id\": 112,\n"
            + "    \"list_group_id\": 12321\n"
            + "  },\n"
            + "  \"recipients\": [\n"
            + "    {\n"
            + "      \"return_path\": \"return-path-wilmaflin@tstone.com\",\n"
            + "      \"address\": {\n"
            + "        \"email\": \"wilmaflin@yahoo.com\",\n"
            + "        \"name\": \"Wilma\"\n"
            + "      },\n"
            + "      \"metadata\": {\n"
            + "        \"place\": \"Bedrock\"\n"
            + "      },\n"
            + "      \"substitution_data\": {\n"
            + "        \"subrcptkey\": \"subrcptvalue\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"greeting\",\n"
            + "        \"prehistoric\",\n"
            + "        \"fred\",\n"
            + "        \"flintstone\"\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"return_path\": \"return-path-abc@tstone.com\",\n"
            + "      \"address\": {\n"
            + "        \"email\": \"abc@flintstone.com\",\n"
            + "        \"name\": \"ABC\"\n"
            + "      },\n"
            + "      \"metadata\": {\n"
            + "        \"place\": \"MD\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"driver\",\n"
            + "        \"computer science\",\n"
            + "        \"fred\",\n"
            + "        \"flintstone\"\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"return_path\": \"return-path-def@tstone.com\",\n"
            + "      \"address\": {\n"
            + "        \"email\": \"fred.jones@flintstone.com\",\n"
            + "        \"name\": \"Grad Student Office\",\n"
            + "        \"header_to\": \"grad-student-office@flintstone.com\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"driver\",\n"
            + "        \"computer science\",\n"
            + "        \"fred\",\n"
            + "        \"flintstone\"\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}";

    //private List<String> expected = Arrays.asList("greeting", "prehistoric", "fred", "flintstone");

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
    public void testDecodeRecipientAttributes() {
        Gson gson = new Gson();
        RecipientList recipientList = gson.fromJson(this.RECIPIENT_LIST_JSON, RecipientList.class);
        Assert.assertNotNull(recipientList);

        Assert.assertEquals(recipientList.getId(), "unique_id_4_graduate_students_list");
        Assert.assertEquals(recipientList.getName(), "graduate_students");
        Assert.assertEquals(recipientList.getDescription(), "An email list of graduate students at UMBC");

        Map<String, String> attributes = recipientList.getAttributes();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(attributes);

        List<RecipientAttributes> recipients = recipientList.getRecipients();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(recipients);
    }

    /**
     *
     */
    @Test
    public void testRecipientAttributesRoundtrip() {
        Gson gson = new Gson();
        RecipientList recipientList = gson.fromJson(this.RECIPIENT_LIST_JSON, RecipientList.class);
        Assert.assertNotNull(recipientList);

        String recipientList_json = recipientList.toJson();
        RecipientList recipientList2 = gson.fromJson(recipientList_json, RecipientList.class);

        Assert.assertEquals(recipientList.getId(), recipientList2.getId());
        Assert.assertEquals(recipientList.getName(), recipientList2.getName());
        Assert.assertEquals(recipientList.getDescription(), recipientList2.getDescription());

        Map<String, String> attributes = recipientList2.getAttributes();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(attributes);

        List<RecipientAttributes> recipients = recipientList2.getRecipients();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(recipients);
    }

    /**
     *
     */
    @Test
    public void testRecipientsListToStoredRecipientsListConvertation() {

        Gson gson = new Gson();
        RecipientList recipientList = gson.fromJson(this.RECIPIENT_LIST_JSON, RecipientList.class);
        StoredRecipientList storedRecipientsList = recipientList.asStoredRecipientList();
        Assert.assertEquals(storedRecipientsList.getListId(), "unique_id_4_graduate_students_list");
    }
}
