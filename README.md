<a href="https://www.sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

[Sign up](https://app.sparkpost.com/join?plan=free-0817?src=Social%20Media&sfdcid=70160000000pqBb&pc=GitHubSignUp&utm_source=github&utm_medium=social-media&utm_campaign=github&utm_content=sign-up) for a SparkPost account and visit our [Developer Hub](https://developers.sparkpost.com) for even more content.

# SparkPost Java Library

[![Build Status](https://travis-ci.org/SparkPost/java-sparkpost.svg?branch=master)](https://travis-ci.org/SparkPost/java-sparkpost) [![Slack Status](http://slack.sparkpost.com/badge.svg)](http://slack.sparkpost.com)

Use this library in Java applications to easily access the SparkPost Email API in your application.

## Version Compatibility Note

### Version 0.6.2 -> 0.6.3

Due to [issue 57](https://github.com/SparkPost/java-sparkpost/issues/57) and to maintain compatibility with old and new version of Apache HTTP Client `SPARKPOST_BASE_URL` must not end with a `/` slash.

### Version 0.12 -> 0.13

Although we try to maintain library backward compatibility this migration may require some minor changes to your code. Substitution data was changed from `Map<String, String>` to `Map<String, Object>`. Most client code will just need to change their map to this new signature.

## Getting Started

The SparkPost Java Library is available in this [Maven Repository](https://repo.maven.apache.org/maven2/com/sparkpost/sparkpost-lib) or in GitHub [Releases](https://github.com/SparkPost/java-sparkpost/releases).


```xml
<dependency>
	<groupId>com.sparkpost</groupId>
	<artifactId>sparkpost-lib</artifactId>
	<version>0.25</version>
</dependency>
```

## Building SparkPost4J

## Basic Send Email Example

```java

package com.sparkpost;

import com.sparkpost.exception.SparkPostException;

public class SparkPost {

    public static void main(String[] args) throws SparkPostException {
        String API_KEY = "YOUR API KEY HERE!!!";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");

    }
}

```

## Basic Send Email through SparkPost EU 

```java


package com.sparkpost;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.transport.IRestConnection;

public class SparkPost {

    public static void main(String[] args) throws SparkPostException {
        String API_KEY = "YOUR API KEY HERE!!!";

        // To use the SparkPost EU use the IRestConnection.SPC_EU_ENDPOINT endpoint
        Client client = new Client(API_KEY, IRestConnection.SPC_EU_ENDPOINT);

        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");

    }
}

```



## Advanced Send Email Example

With SparkPost you have complete control over all aspects of an email and a powerful templating solution.

```java

private void sendEmail(String from, String[] recipients, String email) throws SparkPostException {
	TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

	// Populate Recipients
	List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
	for (String recipient : recipients) {
	RecipientAttributes recipientAttribs = new RecipientAttributes();
		recipientAttribs.setAddress(new AddressAttributes(recipient));
		recipientArray.add(recipientAttribs);
	}
	transmission.setRecipientArray(recipientArray);

	 // Populate Substitution Data
    Map<String, Object> substitutionData = new HashMap<String, Object>();
    substitutionData.put("yourContent", "You can add substitution data too.");
    transmission.setSubstitutionData(substitutionData);

    // Populate Email Body
    TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
    contentAttributes.setFrom(new AddressAttributes(from));
    contentAttributes.setSubject("Your subject content here. {{yourContent}}");
    contentAttributes.setText("Your Text content here.  {{yourContent}}");
    contentAttributes.setHtml("<p>Your <b>HTML</b> content here.  {{yourContent}}</p>");
    transmission.setContentAttributes(contentAttributes);

	// Send the Email
	RestConnection connection = new RestConnection(client, getEndPoint());
	Response response = ResourceTransmissions.create(connection, 0, transmission);

	logger.debug("Transmission Response: " + response);
}

```

## Running The Sample Apps

The sample apps are held in `apps/sparkpost-samples-app` with each sample's source code in `apps/sparkpost-samples-app/src/main/java/com/sparkpost/samples/`.

To build the samples:

```bash
cd apps/sparkpost-samples-app
mvn compile
```

One the samples are built, create `config.properties` by copying `apps/sparkpost-samples-app/config.properties.example` and filling in your SparkPost API key and other test parameters.

You can now run your chosen sample through maven:

```bash
mvn exec:java -Dexec.mainClass=com.sparkpost.samples.SendEmailCCSample
```

