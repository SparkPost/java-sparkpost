#! /usr/bin/env bash

# web-client/tools/bin
bin=`dirname "$0"`

# web-client
bin=`cd $bin/../..; pwd`
echo "project base directory: $bin"

echo "Checking project..."

version=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | egrep -v '^\[|Downloading:' | tr -d ' \n' | sed -E 's/\[.*\]//g')
echo "Current project version: $version"

if [ $# == 0 ]
then
	echo "------------------------------------------------ "

    echo "Usage Error: you must specify two arguments..."
	echo "Usage: doRelease [FROM_VERSION] [TO_VERSION]"
	echo " "
	echo "     Example: doRelease $version 0.0.1"
	echo " "
	echo "------------------------------------------------ "
	echo " "
	exit -1
fi

toVersion='0.0.1'
# Single argument
if [ $# == 1 ]
then
	echo "doing single argument path ($1)"
	toVersion = $1
fi


# Single argument
if [ $# == 2 ]
then
	echo "doing two argument path ($1,$2)"
	version=$1
	toVersion=$2
fi


echo "Will change project from $version to $toVersion"

echo reset Build.java
git checkout ./libs/sparkpost-lib/src/main/java/com/sparkpost/Build.java 

# Update pom files with new version
mvn versions:set -DoldVersion=$version -DnewVersion=$toVersion


GIT_LOG_LINES=`git log --oneline | wc -l | sed -e 's/^[[:space:]]*//'`
TGT_BUILD_FILE="./libs/sparkpost-lib/src/main/java/com/sparkpost/Build.java "
SHORT_HASH=`git log -1 --pretty=format:%h`
LONG_HASH=`git log -1 --pretty=format:%H`
CURRENT_DATE=`date`
JOB_NAME="SparkPost Library"

echo "log lines: $GIT_LOG_LINES" 

echo "[PRE_BUILD_SCRIPT] ${TGT_BUILD_FILE} BEFORE adding build specifics"
echo "`grep = ${TGT_BUILD_FILE}`"
perl -p -i -e "s/BUILD_VERSION_NAME/${toVersion}/" ${TGT_BUILD_FILE}
perl -p -i -e "s/BUILD_GENERATED_NAME/${JOB_NAME}/" ${TGT_BUILD_FILE}
perl -p -i -e "s/BUILD_GENERATED_VERSION/${GIT_LOG_LINES}/" ${TGT_BUILD_FILE}
perl -p -i -e "s/BUILD_GENERATED_SHORT_GIT_HASH/${SHORT_HASH:0:7}/" ${TGT_BUILD_FILE}
perl -p -i -e "s/BUILD_GENERATED_GIT_HASH/${LONG_HASH}/" ${TGT_BUILD_FILE}
perl -p -i -e "s/BUILD_GENERATED_DATE/${CURRENT_DATE}/" ${TGT_BUILD_FILE}
echo "[PRE_BUILD_SCRIPT] ${TGT_BUILD_FILE} AFTER adding build specifics"
echo "`grep = ${TGT_BUILD_FILE}`"




# Done
echo " "
echo "*** You SHOULD do a 'git diff' to make sure everything looks correct before pushing to master."
echo " "
