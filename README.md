# Introduction
A spring boot scaffold for developing.

# How to use

```shell
git clone git@github.com:tsfans/spring-boot-scaffold.git
cd spring-boot-scaffold
# generate maven archetype and install it into local maven repository
sh tool.sh -ga

cd ${your-workspace}
# generate project according to the archetype
mvn archetype:generate -DarchetypeGroupId=com.github.tsfans -DarchetypeArtifactId=spring-boot-scaffold-archetype -DarchetypeVersion=0.0.1-SNAPSHOT
```
