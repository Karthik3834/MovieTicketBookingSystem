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

import com.ey.dto.request.CreateTheatreRequest;
import com.ey.dto.response.TheatreResponse;
import com.ey.service.TheatreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
	
	@Autowired
	private TheatreService theatreService;
	
	@PostMapping
	public ResponseEntity<TheatreResponse> createTheatre(@Valid @RequestBody CreateTheatreRequest request){
		return theatreService.createTheatre(request);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTheatres(){
		return theatreService.getAllTheatres();
	}
	
	@GetMapping("/{theatreId}")
	public ResponseEntity<TheatreResponse> getTheatreById(@PathVariable Long theatreId){
		return theatreService.getTheatreById(theatreId);
	}
	
	@PutMapping("/{theatreId}")
	public ResponseEntity<TheatreResponse> updateTheatre(@PathVariable Long theatreId,@Valid @RequestBody CreateTheatreRequest request){
		return theatreService.updateTheatre(theatreId, request);
	}
	
	@DeleteMapping("/{theatreId}")
	public ResponseEntity<?> deleteTheatre(@PathVariable Long theatreId){
		return theatreService.deleteTheatre(theatreId);
	}

}
