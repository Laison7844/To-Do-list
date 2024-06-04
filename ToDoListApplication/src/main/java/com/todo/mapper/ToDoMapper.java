package com.todo.mapper;

import com.todo.dto.ToDoDTO;
import com.todo.entity.ToDo;

public class ToDoMapper {
	  public static ToDoDTO mapToToDoDto(ToDo todo){
	        return new ToDoDTO(
	        		todo.getId(),
	        		todo.getTitle(),
	        		todo.getDescription(),   	
	             	todo.isCompleted()      
	        );
	    }

	    public static ToDo mapToToDo(ToDoDTO todoDto){
	    	ToDo todo = new ToDo();
	         todo.setId(todoDto.getId());
	         todo.setTitle(todoDto.getTitle());
	         todo.setDescription(todoDto.getDescription());
	        todo.setCompleted(todoDto.isCompleted());
	      
	         return todo;
	    }
	}
