package com.example.demo.repository;

import java.util.Map;

import com.example.demo.model.ToDo;

public interface IToDoRedisRepository {
	
	void save(ToDo toDo);
	Map<Long, ToDo> findAll();
	ToDo findById(long id);
	void update(ToDo toDo);
	void delete(long id);

}
