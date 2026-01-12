#!/bin/bash
# 封装maven命令

if [[ $1 = '-d' || $1 = 'deploy' ]]; then
	echo '执行命令: ./mvnw deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am'
	./mvnw deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am
elif [[ $1 = '-jr' || $1 = 'jar-run' ]]; then
	echo '执行命令: ./mvnw clean install && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar'
	./mvnw clean install && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar
elif [[ $1 = '-br' || $1 = 'boot-run' ]]; then
	echo '执行命令: cd spring-boot-scaffold-starter && ../mvnw spring-boot:run'
	cd spring-boot-scaffold-starter && ../mvnw spring-boot:run
elif [[ $1 = '-ga' || $1 = 'generate-archetype' ]]; then
    echo '执行命令: ./mvnw archetype:create-from-project && cd target/generated-sources/archetype/ && ../../../mvnw install'
    ./mvnw archetype:create-from-project && cd target/generated-sources/archetype/ && ../../../mvnw install
    echo '指定目录执行以下命令即可按模板骨架生成项目: mvn archetype:generate -DarchetypeGroupId=com.github.tsfans -DarchetypeArtifactId=spring-boot-scaffold-archetype -DarchetypeVersion=0.0.1-SNAPSHOT'
elif [[ $1 = '-pi' || $1 = 'package-image' ]]; then
    echo '执行命令: ./mvnw clean package && docker build -t spring-boot-scaffold-app .'
    ./mvnw clean package && docker build -t spring-boot-scaffold-app .
elif [[ $1 = '-rc' || $1 = 'run-container' ]]; then
    echo '执行命令: docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=docker spring-boot-scaffold-app'
    docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=docker spring-boot-scaffold-app
elif [[ $1 = '-dcu' || $1 = 'compose-up' ]]; then
    echo '执行命令: docker-compose up -d'
    docker-compose up -d
elif [[ $1 = '-dcd' || $1 = 'compose-down' ]]; then
    echo '执行命令: docker-compose down --volumes'
    docker-compose down --volumes
else
	echo '
	用法: ./tool [选项]
	选项:
 	-d,deploy                部署api包到远程仓库
 	-br,boot-run             直接运行项目
 	-jr,jar-run              打包项目然后运行jar文件
 	-ga,generate-archetype   生成项目模板骨架
 	-pi,package-image        生成docker image
 	-rc,run-container        运行docker container
 	-dcu,compose-up          运行docker-compose up
 	-dcd,compose-down        运行docker-compose down --volumes
 	-h,help,...              显示帮助信息
 	'
fi
exit 0
