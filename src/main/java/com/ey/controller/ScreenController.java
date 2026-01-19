package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CreateScreenRequest;
import com.ey.dto.response.ScreenResponse;
import com.ey.service.ScreenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {
	
	@Autowired
	private ScreenService screenService;
	
	@PostMapping
	public ResponseEntity<?> createScreen(@Valid @RequestBody CreateScreenRequest request){
		return screenService.createScreen(request);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllScreens(){
		return screenService.getAllScreens();
	}
	
	@GetMapping("/{screenId}")
	public ResponseEntity<?> getScreenById(@PathVariable Long screenId){
		return screenService.getScreenById(screenId);
	}
	
	@PutMapping("/{screenId}")
	public ResponseEntity<?> updateScreen(@PathVariable Long screenId,
			@Valid @RequestBody CreateScreenRequest request){
		return screenService.updateScreen(screenId, request);
	}
	
	@DeleteMapping("/{screenId}")
	public ResponseEntity<?> deleteScreen(@PathVariable Long screenId){
		return screenService.deleteScreen(screenId);
	}

}
