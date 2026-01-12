package com.github.tsfans.infrastructure.db.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceInitializationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * mapper单测基础配置
 */
@MapperScan("com.github.tsfans.infrastructure.db.mapper")
@SpringBootTest(classes = { DataSourceAutoConfiguration.class,
        DataSourceInitializationAutoConfiguration.class, MybatisAutoConfiguration.class })
public abstract class BaseMapperTest {

}
