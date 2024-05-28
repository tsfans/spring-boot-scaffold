# 使用 JDK 21 作为基础镜像
FROM eclipse-temurin:21-jre

# 将 Spring Boot 应用的 JAR 文件复制到镜像中
COPY spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar /app/spring-boot-scaffold-starter.jar

# 设置工作目录
WORKDIR /app

# 暴露应用的端口
EXPOSE 8080

# 设置环境变量
ENV MYSQL_USERNAME=myuser
ENV MYSQL_PASSWORD=mypassword
ENV REDIS_PASSWORD=myredispassword
ENV ROCKETMQ_USERNAME=myrocketmquser
ENV ROCKETMQ_PASSWORD=myrocketmqpassword
ENV NACOS_USERNAME=mynacosuser
ENV NACOS_PASSWORD=mynacospassword

# 启动 Spring Boot 应用
CMD ["java", "-jar", "spring-boot-scaffold-starter.jar"]
