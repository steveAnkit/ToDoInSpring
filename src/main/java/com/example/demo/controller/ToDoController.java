package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDorepository;
import com.example.demo.utils.ResponseMessage;

@RestController
public class ToDoController extends ResponseHandler{
	
	@Autowired
	private ToDorepository toDorepository;
	
	
	@RequestMapping(path = "/toDos", method = RequestMethod.GET)
	public List<ToDo> getToDos()
	{
		return toDorepository.findAll();
	}
	
	@RequestMapping(path = "/toDos", method = RequestMethod.GET)
	public List<ToDo> getToDo(@RequestParam String )
	{
		return toDorepository.findAll();
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getstart()
	{
		return "started";
	}
	
	
	@RequestMapping(path = "/toDos", method = RequestMethod.POST)
	public ResponseEntity<?> addToDo(@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response)
	{
		HashMap< String, String > errors = new HashMap< String, String >();
	    HashMap< String, String > warnings = new HashMap< String, String >();
		if(toDo != null)
		{
		   toDorepository.save(toDo);
		   return successResponse(ResponseMessage.TASK_CREATED, warnings, errors, toDo);
			
		}
		else
		{
			return errorResponse(ResponseMessage.TASK_CREATION_FAILED, warnings, errors);
		}
		
	}
	


}
