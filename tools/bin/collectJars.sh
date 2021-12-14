#!/bin/bash

# Do everything relative to the tools/bin directory
cd "$(dirname $0)"

VERSION=0.25

cd ../..

mkdir target/files

cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION-javadoc.jar ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION-javadoc.jar.asc  ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION-sources.jar  ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION-sources.jar.asc ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION.jar ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION-javadoc.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION-javadoc.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION-sources.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION-sources.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION-javadoc.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION-javadoc.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION-sources.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION-sources.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION.jar.asc ./target/files/

cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION.pom ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-$VERSION.pom.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION.pom ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-$VERSION.pom.asc ./target/files/
cp ./apps/target/apps-$VERSION.pom ./target/files/
cp ./apps/target/apps-$VERSION.pom.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION.pom ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-$VERSION.pom.asc ./target/files/
cp ./libs/target/libs-$VERSION.pom ./target/files/
cp ./libs/target/libs-$VERSION.pom.asc ./target/files/
cp ./target/sparkpost-$VERSION.pom ./target/files/
cp ./target/sparkpost-$VERSION.pom.asc ./target/files/







