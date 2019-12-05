package com.example.demo.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ToDo;

@Repository
public class ToDoRedisRepositoryImpl implements IToDoRedisRepository {

	private RedisTemplate<String, ToDo> redisTemplate;

	private HashOperations<String, Long, ToDo> hashOp;

	private static final String TO_DO = "TODO";

	public ToDoRedisRepositoryImpl(RedisTemplate<String, ToDo> redisTemplate, HashOperations<?, ?, ?> hashOp) {
		super();
		this.redisTemplate = redisTemplate;
		this.hashOp = redisTemplate.opsForHash();
	}

	@Override
	public void save(ToDo toDo) {
		hashOp.put(TO_DO, toDo.getId(), toDo);
	}

	@Override
	public Map<Long, ToDo> findAll() {
		return hashOp.entries(TO_DO);
	}

	@Override
	public ToDo findById(long id) {
		return hashOp.get(TO_DO, id);
	}

	@Override
	public void update(ToDo toDo) {
		hashOp.put(TO_DO, toDo.getId(), toDo);
	}

	@Override
	public void delete(long id) {
		hashOp.delete(TO_DO, id);

	}

}
