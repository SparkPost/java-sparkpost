<a href="http://sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

# Contributing to java-sparkpost

Transparency is one of our core values, and we encourage developers to contribute and become part of the SparkPost developer community.

The following is a set of guidelines for contributing to java-sparkpost, which is hosted in the [SparkPost Organization](https://github.com/sparkpost) on GitHub. These are just guidelines, not rules, use your best judgment and feel free to propose changes to this document in a pull request.

## Submitting Issues

* You can create an issue [here](https://github.com/sparkpost/java-sparkpost/issues/new), but before doing that please read the notes below on debugging and submitting issues, and include as many details as possible with your report.
* Include the version of java-sparkpost you are using.
* Perform a [cursory search](https://github.com/issues?utf8=%E2%9C%93&q=is%3Aissue+user%3Asparkpost+repo%3Ajava-sparkpost) to see if a similar issue has already been submitted.

## Local development

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


## Contribution Steps

### Guidelines

- Provide documentation for any newly added code.
- Provide tests for any newly added code.
- Follow Google [Style Guide](https://google.github.io/styleguide/javaguide.html)

1. Create a new branch named after the issue you’ll be fixing (include the issue number as the branch name, example: Issue in GH is #8 then the branch name should be ISSUE-8))
2. Write corresponding tests and code (only what is needed to satisfy the issue and tests please)
    * Include your tests in the 'test' directory in an appropriate test file
    * Write code to satisfy the tests
3. Ensure automated tests pass
4. Submit a new Pull Request applying your feature/fix branch to the develop branch

## Testing

Once you are set up for local development:

* Run ``mvn clean install`` to test against your current environment

