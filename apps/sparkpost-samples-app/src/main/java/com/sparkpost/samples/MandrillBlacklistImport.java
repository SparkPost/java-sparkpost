
package com.sparkpost.samples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SuppressionList;
import com.sparkpost.model.SuppressionListEntry;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceSuppressionList;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Lists all Sending Domains
 */
public class MandrillBlacklistImport extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(MandrillBlacklistImport.class);

    private Client client;

    public static class Fields {

        public static final int EMAIL_COL = 0;
        public static final int REASON_COL = 1;
        public static final int DESCRIPTION_COL = 2;
        public static final int CREATED_AT_COL = 3;
        public static final int EXPIRES_AT_COL = 4;
        public static final int LAST_EVENT_AT_COL = 5;
        public static final int EXPIRES_AT2_COL = 6;
        public static final int SUBACCOUNT_COL = 7;// Unused

    }

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        MandrillBlacklistImport sample = new MandrillBlacklistImport();
        sample.runApp();
    }

    /*
     * How to export Mandrill blacklist: https://mandrill.zendesk.com/hc/en-us/articles/205582997
     * Beware some samples I've received from Mandrill are not 100% correctly formated. I've found it necessary to remove some corrupt lines.
     */
    private void runApp() throws SparkPostException, IOException {

        this.client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(this.client, getEndPoint());

        SuppressionList suppressionList = new SuppressionList();
        suppressionList.setRecipients(new ArrayList<SuppressionListEntry>());

        // Load sample Mandrill Blacklist file
        String csvFile = "samples/mandrillBlacklistExample.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        int row = 0;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));

            while ((line = br.readLine()) != null) {
                row++;
                if (StringUtils.isEmpty(line)) {
                    System.out.println("Warning: LN:" + row + " is empty");
                    continue;
                }
                String[] entryRow = line.split(cvsSplitBy);

                if (entryRow.length < Fields.EXPIRES_AT2_COL) {
                    System.out.println("Error: LN:" + row + " too short (" + entryRow.length + "),\n \"" + line + "\"\n");
                }

                String email = entryRow[Fields.EMAIL_COL];
                System.out.println("Parse: " + line);

                if (StringUtils.isEmpty(email)) {
                    // Ignore rows that do not have an email address since it is required. Should we fail instead?
                    System.out.println("Ignore header row");
                    continue;
                } else if (email.equalsIgnoreCase("email")) {
                    // First row is column names so skip it
                    System.out.println("Warning: LN:" + row + " Ignore blank email row\"");
                    continue;
                } else if (StringUtils.isEmpty(entryRow[Fields.REASON_COL]) || !entryRow[Fields.REASON_COL].equalsIgnoreCase("hard-bounce")) {
                    // ignore empty reason or soft-bounces
                    //System.out.println("Ignore reason: " + entryRow[Fields.REASON_COL]);
                    System.out.println("LN:" + row + " Ignore reason: " + entryRow[Fields.REASON_COL]);
                    continue;
                }

                System.out.println("LN:" + row + " adding " + entryRow[Fields.EMAIL_COL]);

                SuppressionListEntry entry = new SuppressionListEntry();
                // MBL: = Mandrill Blacklist
                entry.setDescription("MBL: " + line);
                entry.setEmail(entryRow[Fields.EMAIL_COL]);
                // Assumes Mandrill blacklist is only for non-transactional email
                entry.setType(SuppressionListEntry.TypeTypes.TRANSACTIONAL_TYPE);

                // Leave off source so it is set to "Manually Added"
                // entry.setSource(null);

                // Maybe a Set is better to make sure addresses are unique
                suppressionList.getRecipients().add(entry);
            }

        } finally {
            System.out.println("Last row: " + row);
            System.out.println("Last Line: " + line);
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");

        if (suppressionList.getRecipients() == null || suppressionList.getRecipients().size() == 0) {
            System.out.println("There are no members of blacklist to add or update");
            return;

        }
        Response result = ResourceSuppressionList.insertOrUpdateBulk(connection, suppressionList);
        System.out.println("Supression List Result: " + result);

    }
}
