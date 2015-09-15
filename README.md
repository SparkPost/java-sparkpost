<a href="http://sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

You can sign up for a Stripe account at https://sparkpost.com

# SparkPost Java SDK

Use this repository in Java applications to easily access the SparkPost Email APIs in your application.

## Getting Started

The SparkPost Java API is available in this [Maven Repository](http://maven.apache.org/download.cgi):

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

## TODO: Examples

## TODO: Contributions

## License: Apache 2
Copyright (c) 2015

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
