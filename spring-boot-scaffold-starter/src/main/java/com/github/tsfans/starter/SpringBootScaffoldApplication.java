package com.github.tsfans.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.github.tsfans.infrastructure.db.mapper")
@SpringBootApplication(scanBasePackages = "com.github.tsfans")
public class SpringBootScaffoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScaffoldApplication.class, args);
	}
}
