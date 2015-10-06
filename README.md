<a href="http://sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

You can sign up for a SparkPost account at https://sparkpost.com

# SparkPost Java Library

Use this library in Java applications to easily access the SparkPost Email API in your application.

## Getting Started

The SparkPost Java Library is available in this [Maven Repository](http://maven.apache.org/download.cgi):

__NOTE: This is not published to Maven Repo yet!!!__

```xml
<dependency>
	<groupId>com.sparkpost</groupId>
	<artifactId>client-server-protocol-lib</artifactId>
	<version>0.8-SNAPSHOT</version>
</dependency>
```

## Building SparkPost4J

### Prerequisites

1. Download and install [Java JDK](https://java.com/en/download/) >= 1.7

2. Download and setup [Maven](https://maven.apache.org/) >= 3.3

3. Setup an account at [SparkPost.com](http://sparkpost.com)

4. Create API Key in your [SparkPost Account](https://app.sparkpost.com/account/credentials)

4. For examples to run you will need to setup the following environment variables:
	* `SPARKPOST_API_KEY="YOUR_SPARKPOST_API_KEY"`
   * `SPARKPOST_SENDER_EMAIL="YOUR_EMAIL_FROM_ADDRESS"`

5. Optionally setup [Eclipse](https://eclipse.org/) (will need Maven Plugin to import project)

6. Optionally setup [IntelliJ](https://www.jetbrains.com/idea/)

### Building

1. Clone the repository
  `git clone https://github.com/sparkpost/java-sparkpost`
 
2. Build with Maven `mvn clean install`

### GUI Editors

#### Eclipse

It is easiest if you build the project on the command-line first before importing project into Eclipse.

* Import project into Eclipse
	* Setup Lombok in Eclipse `./tools/bin/setupLombok.sh` (browse to and select eclipse.ini)
	* Restart Eclipse
   * File -> Import -> Maven -> Existing Maven Projects
   * See "Prerequisites" above for environment variables needed for running examples

#### IntelliJ

* Import project into IntelliJ
	* File -> New -> Project from Existing Sources
	* Download and install the Lombok plugin https://plugins.jetbrains.com/plugin/6317

## Send Email Example

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
		
	transmission.setReturnPath(from);
		
	// Populate Substitution Data
	Map<String, String> substitutionData = new HashMap<String, String>();
	substitutionData.put("from", from);
	transmission.setSubstitutionData(substitutionData);
	
	// Populate Email Body
	TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
	contentAttributes.setEmailRFC822(email);
	transmission.setContentAttributes(contentAttributes);
	
	// Send the Email
	RestConnection connection = new RestConnection(client, getEndPoint());
	Response response = ResourceTransmissions.create(connection, 0, transmission);
	
	logger.debug("Transmission Response: " + response);
}

```

