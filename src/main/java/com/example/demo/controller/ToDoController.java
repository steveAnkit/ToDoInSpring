package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDorepository;
import com.example.demo.utils.ResponseMessage;

@RestController
@RequestMapping("api/v1")
public class ToDoController extends ResponseHandler {

	@Autowired
	private ToDorepository toDorepository;
	
	@GetMapping(path = "/")
	public String getstart() {
		return "started";
	}



	@GetMapping(path = "/toDos")
	public ResponseEntity<?> getToDos() {
		List<ToDo> toDos = null;
		HashMap<String, String> errors = new HashMap<String, String>();
		HashMap<String, String> warnings = new HashMap<String, String>();

		toDos = toDorepository.findAll();

		if (toDos == null) {
			return errorResponse(ResponseMessage.TASK_LIST_FAILED, warnings, errors);
		} else {
			return successResponse(ResponseMessage.MESSAGE_GENERAL_SUCCESS, warnings, errors, toDos);
		}

	}

	
	@GetMapping("/toDo/{taskId}")
	public Optional<ToDo> getTask(@PathVariable long taskId)
	{
		Optional<ToDo> toDo =  toDorepository.findById(taskId);
		
		if(toDo.isPresent())
		{
			return toDo;
		}
		else {
			 throw new UserNotFoundException("Task not found for task id: "+ taskId );
		}
	}
	
	@PostMapping(path = "/toDos")
	public ResponseEntity<?> addToDo(@Valid @RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> errors = new HashMap<String, String>();
		HashMap<String, String> warnings = new HashMap<String, String>();
		if (toDo != null) {
			ToDo SavedTask = toDorepository.save(toDo);

			/*
			 * This is the best practice of sending the HTTP Status Code and location of the
			 * created resource
			 */

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskID}")
					.buildAndExpand(SavedTask.getId()).toUri();

			return ResponseEntity.created(location).build();

		} else {
			return errorResponse(ResponseMessage.TASK_CREATION_FAILED, warnings, errors);
		}

	}

	@DeleteMapping("/toDos/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable int taskId) {

		HashMap<String, String> errors = new HashMap<String, String>();
		HashMap<String, String> warnings = new HashMap<String, String>();

		if (taskId != 0) {
			int rows = toDorepository.deleteTask(taskId);
			return successResponse(ResponseMessage.MESSAGE_GENERAL_SUCCESS, warnings, errors, rows);
		} else {
			return errorResponse(ResponseMessage.FAILED, warnings, errors);

		}
	}

}

