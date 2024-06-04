package com.todo.service;

import java.util.List;

import com.todo.dto.ToDoDTO;

public interface ToDoService {
	
	public String addTask(ToDoDTO task);

	public ToDoDTO findTask(int id);

	public List<ToDoDTO> findAllTask();

	public ToDoDTO updateToDoById(int id,ToDoDTO task);

	public void deleteToDoById(int id);
	
	public ToDoDTO toDoCompleted(int id);
	
	public ToDoDTO toDoNotCompleted(int id);
	
	
}
