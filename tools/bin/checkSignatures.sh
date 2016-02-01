#!/bin/bash

# Do everything relative to the tools/bin directory
cd "$(dirname $0)"

cd ../..

echo "checking parent poms"

echo ""
echo "./target/sparkpost-*.pom"
gpg --verify  ./target/sparkpost-*.pom.asc ./target/sparkpost-*.pom

echo ""
echo "./target/libs-*.pom"
gpg --verify  ./libs/target/libs-*.pom.asc ./libs/target/libs-*.pom

echo ""
echo "checking build artifacts"

echo ""
echo "./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-javadoc.jar"
gpg --verify  ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-javadoc.jar.asc ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-javadoc.jar

echo ""
echo "./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-sources.jar"
gpg --verify ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-sources.jar.asc ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*-sources.jar

echo ""
echo "./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*.jar"
gpg --verify ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*.jar.asc ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-*.jar

echo ""
echo "./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-javadoc.jar"
gpg --verify ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-javadoc.jar.asc ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-javadoc.jar

echo ""
echo "./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-sources.jar"
gpg --verify ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-sources.jar.asc ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*-sources.jar

echo ""
echo "./apps/sparkpost-samples-app/target/sparkpost-samples-app-*.jar"
gpg --verify ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*.jar.asc ./apps/sparkpost-samples-app/target/sparkpost-samples-app-*.jar

echo ""
echo "./libs/sparkpost-lib/target/sparkpost-lib-*-javadoc.jar"
gpg --verify ./libs/sparkpost-lib/target/sparkpost-lib-*-javadoc.jar.asc ./libs/sparkpost-lib/target/sparkpost-lib-*-javadoc.jar

echo ""
echo "./libs/sparkpost-lib/target/sparkpost-lib-*-sources.jar"
gpg --verify ./libs/sparkpost-lib/target/sparkpost-lib-*-sources.jar.asc ./libs/sparkpost-lib/target/sparkpost-lib-*-sources.jar

echo ""
echo "./libs/sparkpost-lib/target/sparkpost-lib-*.jar"
gpg --verify ./libs/sparkpost-lib/target/sparkpost-lib-*.jar.asc ./libs/sparkpost-lib/target/sparkpost-lib-*.jar

echo ""
echo ""
echo "Done"



