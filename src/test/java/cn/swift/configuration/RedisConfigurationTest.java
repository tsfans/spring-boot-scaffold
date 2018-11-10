package cn.swift.configuration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import cn.swift.SpringBootScaffoldApplicationTests;

public class RedisConfigurationTest extends SpringBootScaffoldApplicationTests {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    ValueOperations<String, String> vo;

    @BeforeEach
    void init() {
	vo = redisTemplate.opsForValue();
    }

    @Test
    public void operateRedis() {
	vo.set("test", "abc123");
	assertTrue(vo.get("test").equals("abc123"));
    }
}
