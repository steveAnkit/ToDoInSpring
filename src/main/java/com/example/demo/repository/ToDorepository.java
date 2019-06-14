package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ToDo;

@Repository
public interface ToDorepository extends JpaRepository<ToDo, Long>{
	
   List<ToDo> findByUserName(String userName);
   
   List<ToDo> findAll();
   
	/*
	 * @Modifying annotation above the repository method because it modifies the
	 * state of the database and does not select data
	 */
   
	/*
	 * clearAutomatically = true ensures that the EntityManager is automatically
	 * cleared when the query has executed, ensuring that no entities are
	 * unsynchronized
	 */
   
   @Transactional
   @Modifying(clearAutomatically = true)
   @Query(value = "update todos set hide = 1 where id = :taskId ", nativeQuery = true )
   int deleteTask(@Param("taskId") int taskId);
   
   
}
