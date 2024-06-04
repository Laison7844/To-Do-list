package com.todo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.dao.ToDoDao;
import com.todo.dto.ToDoDTO;
import com.todo.entity.ToDo;
import com.todo.exception.ResourceNotFoundException;
import com.todo.mapper.ToDoMapper;
import com.todo.service.ToDoService;

@Service
public class ToDoServiceImp implements ToDoService {
	@Autowired
	ToDoDao dao;
	
	public String addTask(ToDoDTO todoDto) {
		ToDo todo=ToDoMapper.mapToToDo(todoDto);
		dao.save(todo);
		return "successfully added";
	}

	public ToDoDTO findTask(int id) {
		ToDo todo = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("To-Do-Task with id" + id + "does not exist"));
		return ToDoMapper.mapToToDoDto(todo);
	}

	public List<ToDoDTO> findAllTask() {
		List<ToDo> lists = dao.findAll();
		return lists.stream().map((list) -> ToDoMapper.mapToToDoDto(list))
                .collect(Collectors.toList());
	}

	public ToDoDTO updateToDoById(int id,ToDoDTO todoDto) {
		ToDo todo = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("To-Do-Task with id" + id + "does not exist"));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		   ToDo updatedToDo = dao.save(todo);
	        return ToDoMapper.mapToToDoDto(updatedToDo);
	    }

	public void deleteToDoById(int id) {
		ToDo todo = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("To-Do-Task with id" + id + "does not exist"));
		dao.deleteById(id);
	}

	

	public ToDoDTO toDoCompleted(int id) {
	ToDo todo=dao.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("To-Do-Task with id"+id+"does not found"));
	    todo.setCompleted(true);
	  ToDo completed=dao.save(todo);
	return ToDoMapper.mapToToDoDto(completed);
        }
	
	public ToDoDTO toDoNotCompleted(int id) {
		ToDo todo=dao.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("To-Do-Task with id"+id+"does not found"));
		todo.setCompleted(false);
		  ToDo inCompleted=dao.save(todo);
		return ToDoMapper.mapToToDoDto(inCompleted);
	        }
	
}
