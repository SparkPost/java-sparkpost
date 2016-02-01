#!/bin/bash

BIN_DIR=`dirname "$0"`
cd $BIN_DIR/../..

BASE_DIR=`pwd`

echo "Base Dir: " $BASE_DIR

# Bundle SparkPost Lib

cd $BASE_DIR/libs/sparkpost-lib/target
gpg --verify sparkpost-*.pom.asc sparkpost-*.pom

jar -cvf bundle.jar sparkpost-lib-*-javadoc.jar sparkpost-lib-*-javadoc.jar.asc sparkpost-lib-*-sources.jar sparkpost-lib-*-sources.jar.asc sparkpost-lib-*.jar sparkpost-lib-*.jar.asc sparkpost-lib-*.pom sparkpost-lib-*.pom.asc

# Bundle base libs
cd $BASE_DIR/libs/target
jar -cvf bundle.jar *


# Bundle target
cd $BASE_DIR/target
jar -cvf bundle.jar *


