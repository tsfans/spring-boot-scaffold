# Introduction

A spring boot scaffold for developing.

## Prerequisites

- JDK 21

## How to use

```shell
git clone git@github.com:tsfans/spring-boot-scaffold.git
cd spring-boot-scaffold
# generate maven archetype and install it into local maven repository
sh tool.sh -ga

cd ${your-workspace}
# generate project according to the archetype
mvn archetype:generate -DarchetypeGroupId=com.github.tsfans -DarchetypeArtifactId=spring-boot-scaffold-archetype -DarchetypeVersion=0.0.1-SNAPSHOT
Define value for property 'groupId': your-group-id
Define value for property 'artifactId': your-artifact-id
Define value for property 'version' 1.0-SNAPSHOT: your-version
Define value for property 'package' your-group-id: your-package
Y: : Y
```

## Project Structure

- spring-boot-scaffold-exporter
  - spring-boot-scaffold-api // service entry point.
  - spring-boot-scaffold-api-adapter // api implement, such as http、rpc and so on.
- spring-boot-scaffold-application // use case implement by combine domain service.
- spring-boot-scaffold-domain // core business logic.
- spring-boot-scaffold-infrastructure // adapt service, such as database repository, remote service and so on.
- spring-boot-scaffold-starter // project starter.