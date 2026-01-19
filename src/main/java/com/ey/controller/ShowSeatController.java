package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.service.ShowSeatService;

@RestController
@RequestMapping("/api/show-seats")
public class ShowSeatController {
	
	@Autowired
	private ShowSeatService showSeatService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShowSeats(@RequestParam Long showId, @RequestParam Long screenId){
		return showSeatService.generateShowSeats(showId, screenId);
	}
	
	@GetMapping("/{showId}")
	public ResponseEntity<?> getShowSeats(@PathVariable Long showId){
		return showSeatService.getShowSeats(showId);
	}
	
	@GetMapping("/{showId}/available")
	public  ResponseEntity<?> getAvailableShowSeats(@PathVariable Long showId){
		return showSeatService.getAvailableShowSeats(showId);
	}
	

}
