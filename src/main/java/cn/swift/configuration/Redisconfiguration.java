package cn.swift.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

@Configuration
public class Redisconfiguration {

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(new JedisConnectionFactory());
    redisTemplate.setDefaultSerializer(serializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
