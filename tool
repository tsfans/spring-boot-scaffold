#!/bin/bash
# 封装maven命令

if [[ $1 = '-d' || $1 = 'deploy' ]]; then
	echo '执行命令: mvn deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am'
	mvn deploy -pl ./spring-boot-scaffold-exporter/spring-boot-scaffold-api -am
elif [[ $1 = '-jr' || $1 = 'jar-run' ]]; then
	echo '执行命令: mvn clean install package && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar'
	mvn clean install package && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar
elif [[ $1 = '-br' || $1 = 'boot-run' ]]; then
	echo '执行命令: cd spring-boot-scaffold-starter && mvn spring-boot:run'
	cd spring-boot-scaffold-starter && mvn spring-boot:run
else
	echo '
	用法: ./tool [选项]
	选项:
 	-d,deploy        部署api包到远程仓库
 	-br,boot-run     直接运行项目
 	-jr,jar-run      打包项目然后运行jar文件    
 	-h,help,...      显示帮助信息
 	'
fi
exit 0
