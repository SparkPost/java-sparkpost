![SparkPost Logo](https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg =75x)

# SparkPost Java SDK

Use this repository in Java applications to easily access the SparkPost Email APIs in your application.

## Installation

1. Clone the repository
  `git clone https://github.com/sparkpost/java-sparkpost`

2. [OPTIONAL] Import project into Eclipse

  File -> Import -> Maven -> Existing Maven Projects

3. Build the .jar file via CLI:
  `mvn -DskipTests package`

4. Add the .jar file and dependencies (<a href="http://commons.apache.org/proper/commons-beanutils/">Apache Commons BeanUtils</a>, <a href="http://logging.apache.org/log4j/1.2/">Apache log4j 1.x</a>, and <a href="https://code.google.com/p/google-gson/">Google Gson</a>) to your class path

5. Obtain a valid SparkPost API Key from [SparkPost](https://sparkpost.com) and place into the `sparkpostsdk.properties` file using `src/main/resources/sparkpost.properties` file as a starting template

## TODO: Getting Started

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
