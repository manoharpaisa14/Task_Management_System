package com.task_management.operations_tm.controller;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_management.operations_tm.model.RestUser;

@RestController
@RequestMapping("/task")
@Validated
public class Controller {
	
	Map<String,RestUser> alltasks = new HashMap<>();
    
	@GetMapping
	public Collection<RestUser> getMethod() {
		return alltasks.values();
	}
	
	
	 @GetMapping("/{taskId}")
	    public ResponseEntity<RestUser> getTaskById(@PathVariable String taskId) {
	        RestUser task = alltasks.get(taskId);
	        if (task == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(task, HttpStatus.OK);
	    }
	 
	 
	@PostMapping
	public ResponseEntity<String> postMethod(@Validated @RequestBody RestUser taskdetails) {
		 
		 String taskId = UUID.randomUUID().toString();
		RestUser addtask = new RestUser();
		
		addtask.setId(taskId);
		addtask.setTitle(taskdetails.getTitle());
		addtask.setDescription(taskdetails.getDescription());
		addtask.setDue_date(taskdetails.getDue_date());
		addtask.setStatus(taskdetails.getStatus());
		
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        addtask.setCreated_at(currentTimestamp);
        addtask.setUpdated_at(currentTimestamp);
        
		alltasks.put(taskId,addtask);
		return new ResponseEntity<>("Task added",HttpStatus.CREATED);
	}
	
	
	@PutMapping(path="/{taskId}")
	public ResponseEntity<String> putMethod(@PathVariable String taskId,@RequestBody RestUser taskdetails) {
		if(!alltasks.containsKey(taskId)) {
			return new ResponseEntity<>("Task ID not found",HttpStatus.BAD_REQUEST);
		}	
			RestUser existT = alltasks.get(taskId);

			existT.setTitle(taskdetails.getTitle());
			existT.setDescription(taskdetails.getDescription());
			existT.setDue_date(taskdetails.getDue_date());
			existT.setStatus(taskdetails.getStatus());
			
			existT.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            alltasks.put(taskId, existT);
            return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
		}
	
	
	@PatchMapping(path="/{taskId}")
	public ResponseEntity<String> patchMethod(@PathVariable String taskId, @RequestBody RestUser taskdetails) {
		if(!alltasks.containsKey(taskId)) {
			return new ResponseEntity<>("user ID not found",HttpStatus.NOT_FOUND);
			
		}
			RestUser existT = alltasks.get(taskId);
			
			if(taskdetails.getTitle() != null) {
				existT.setTitle(taskdetails.getTitle());
			}
			if (taskdetails.getDescription() != null) {
                existT.setDescription(taskdetails.getDescription());
            }
            if (taskdetails.getDue_date() != null) {
                existT.setDue_date(taskdetails.getDue_date());
            }
            if (taskdetails.getStatus() != null) {
                existT.setStatus(taskdetails.getStatus());
            }
            existT.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            alltasks.put(taskId, existT); 

		return new ResponseEntity<>("Task updated successfully",HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(path="/{taskId}")
	public ResponseEntity<String> deleteMethod(@PathVariable String taskId) {
		
		if(!alltasks.containsKey(taskId)) {
			return new ResponseEntity<>("task ID not found",HttpStatus.NOT_FOUND);
		}
		else {
			alltasks.remove(taskId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
