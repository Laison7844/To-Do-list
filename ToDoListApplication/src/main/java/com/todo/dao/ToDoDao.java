package com.todo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.todo.entity.ToDo;

public interface ToDoDao extends JpaRepository<ToDo, Integer>{
	
}
