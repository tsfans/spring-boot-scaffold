package cn.swift.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.swift.infrastructure.db.mapper")
@SpringBootApplication(scanBasePackages = "cn.swift")
public class SpringBootScaffoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScaffoldApplication.class, args);
	}
}
