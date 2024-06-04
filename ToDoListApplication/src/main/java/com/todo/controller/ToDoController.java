package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.ToDoDTO;
import com.todo.entity.ToDo;
import com.todo.serviceimpl.ToDoServiceImp;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/todolist")
public class ToDoController {
	
	@Autowired
	ToDoServiceImp service;
	
	@PostMapping
	public ResponseEntity <String> addNew(@RequestBody ToDoDTO todoDto) {
		String message=service.addTask(todoDto);
		return new ResponseEntity(message,HttpStatus.CREATED);
	}
	@GetMapping("{id}")
	public ResponseEntity <ToDoDTO> getById(@PathVariable int id) {
		ToDoDTO todoDto=service.findTask(id);
		return new ResponseEntity(todoDto,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<ToDoDTO>> getById() {
		List<ToDoDTO> todoDto =service.findAllTask();
		return new ResponseEntity(todoDto,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity <ToDoDTO> updateToDo(@RequestBody ToDoDTO todos, @PathVariable int id) {
		ToDoDTO todoDto =service.updateToDoById(id,todos);
		return new ResponseEntity(todoDto,HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	ResponseEntity<ToDoDTO> deleteEmployeeById(@PathVariable int id){
		service.deleteToDoById(id);
		return new ResponseEntity("Employee deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/complete/{id}")
	public ResponseEntity<ToDoDTO> completed(@PathVariable int id) {
		ToDoDTO todoDto=service.toDoCompleted(id);
		return new ResponseEntity(todoDto,HttpStatus.OK);
	}

	@GetMapping("/incomplete/{id}")
	public ResponseEntity  Notcompleted(@PathVariable int id) {
		ToDoDTO todoDto=service.toDoNotCompleted(id);
		return new ResponseEntity(todoDto,HttpStatus.OK);
	}
}
