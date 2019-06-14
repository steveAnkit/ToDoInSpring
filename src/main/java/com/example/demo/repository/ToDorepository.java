package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ToDo;

@Repository
public interface ToDorepository extends JpaRepository<ToDo, Long>{
	
   List<ToDo> findByUserName(String userName);
   
   List<ToDo> findAll();
   

}
