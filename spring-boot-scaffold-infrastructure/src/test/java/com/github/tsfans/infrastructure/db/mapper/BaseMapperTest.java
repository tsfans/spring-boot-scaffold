package com.github.tsfans.infrastructure.db.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * mapper单测基础配置
 */
@MapperScan("com.github.tsfans.infrastructure.db.mapper")
@SpringBootTest(classes = { DataSourceAutoConfiguration.class,
        SqlInitializationAutoConfiguration.class, MybatisAutoConfiguration.class })
public abstract class BaseMapperTest {

}
