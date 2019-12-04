package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.model.ToDo;

@SpringBootApplication
public class ToDoApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

}
