#!/bin/bash

# Do everything relative to the tools/bin directory
cd "$(dirname $0)"

cd ../..

mkdir target/files

cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8-javadoc.jar ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8-javadoc.jar.asc  ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8-sources.jar  ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8-sources.jar.asc ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8.jar ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8-javadoc.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8-javadoc.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8-sources.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8-sources.jar.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8.jar ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8-javadoc.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8-javadoc.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8-sources.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8-sources.jar.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8.jar ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8.jar.asc ./target/files/

cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8.pom ./target/files/
cp ./apps/sparkpost-documentor-app/target/sparkpost-documentor-app-0.8.pom.asc ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8.pom ./target/files/
cp ./apps/sparkpost-samples-app/target/sparkpost-samples-app-0.8.pom.asc ./target/files/
cp ./apps/target/apps-0.8.pom ./target/files/
cp ./apps/target/apps-0.8.pom.asc ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8.pom ./target/files/
cp ./libs/sparkpost-lib/target/sparkpost-lib-0.8.pom.asc ./target/files/
cp ./libs/target/libs-0.8.pom ./target/files/
cp ./libs/target/libs-0.8.pom.asc ./target/files/
cp ./target/sparkpost-0.8.pom ./target/files/
cp ./target/sparkpost-0.8.pom.asc ./target/files/







