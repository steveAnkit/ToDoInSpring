package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.model.ToDo;

/**
 * 
 * @author Ankit Sharma
 *
 */


@Configuration
public class RedisConfiguration {
	
	@Bean
	JedisConnectionFactory connectionFactory()
	{
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		// use RedisStandaloneConfiguration
		connectionFactory.setHostName("jedis host");
		connectionFactory.setPort(8083);
		return connectionFactory;
	}
	
	@Bean
	RedisTemplate<String, ToDo> redisTemplate()
	{
		RedisTemplate<String, ToDo> redisTemplate = new RedisTemplate<String, ToDo>();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}
	
	@Bean
	HashOperations<?, ?, ?> hashOperations()
	{
		HashOperations<?, ?, ?> hashOp = redisTemplate().opsForHash();
		return hashOp;
	}
	

}
