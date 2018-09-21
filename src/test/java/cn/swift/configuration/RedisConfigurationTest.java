package cn.swift.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import cn.swift.SpringBootScaffoldApplicationTests;

public class RedisConfigurationTest extends SpringBootScaffoldApplicationTests {

  @Autowired
  RedisTemplate<String, String> redisTemplate;

  ValueOperations<String, String> vo;

  @Before
  public void init() {
    vo = redisTemplate.opsForValue();
  }

  @Test
  public void operateRedis() {
    vo.set("test", "abc123");
    Assert.assertTrue("success", vo.get("test").equals("abc123"));
  }
}
