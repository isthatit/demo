package dev.zergling.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.zergling.demo.dto.ResponseDTO;
import dev.zergling.demo.dto.TodoDTO;
import dev.zergling.demo.model.TodoEntity;
import dev.zergling.demo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		List<String> list = new ArrayList<String>();
		list.add(str);
		ResponseDTO<String> response =  ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
		
	}
	
	@GetMapping("/testTodo")
	public ResponseEntity<?> todoControllerResponseEntity(){
		
		
		List<String> list = new ArrayList<String>();
		list.add("Hello Entity");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			entity.setId(null);
			
			entity.setUserId(temporaryUserId);
			
			List<TodoEntity> entities = service.create(entity);
			
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest() .body(response);

		}
	}

}