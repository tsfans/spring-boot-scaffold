#!/bin/bash
# 封装maven命令

if [[ $1 = '-d' || $1 = 'deploy' ]]; then
	echo '执行命令: mvn deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am'
	mvn deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am
elif [[ $1 = '-jr' || $1 = 'jar-run' ]]; then
	echo '执行命令: mvn clean install && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar'
	mvn clean install && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar
elif [[ $1 = '-br' || $1 = 'boot-run' ]]; then
	echo '执行命令: cd spring-boot-scaffold-starter && mvn spring-boot:run'
	cd spring-boot-scaffold-starter && mvn spring-boot:run
elif [[ $1 = '-ga' || $1 = 'generate-archetype' ]]; then
    echo '执行命令: mvn archetype:create-from-project && cd target/generated-sources/archetype/ && mvn install'
    mvn archetype:create-from-project && cd target/generated-sources/archetype/ && mvn install
    echo '指定目录执行以下命令即可按模板骨架生成项目: mvn archetype:generate -DarchetypeGroupId=com.github.tsfans -DarchetypeArtifactId=spring-boot-scaffold-archetype -DarchetypeVersion=0.0.1-SNAPSHOT'
else
	echo '
	用法: ./tool [选项]
	选项:
 	-d,deploy          部署api包到远程仓库
 	-br,boot-run       直接运行项目
 	-jr,jar-run        打包项目然后运行jar文件
 	-ga,generate-archetype 生成项目模板骨架
 	-h,help,...        显示帮助信息
 	'
fi
exit 0
