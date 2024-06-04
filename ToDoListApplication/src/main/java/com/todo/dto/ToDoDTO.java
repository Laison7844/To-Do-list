package com.todo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDTO {
		int id;
		String title;
		String description;
	    boolean completed;
	

		}

