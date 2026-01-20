package com.ey.controller;

import java.util.List;

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

import com.ey.dto.request.CreateShowRequest;
import com.ey.dto.response.ShowResponse;
import com.ey.service.ShowService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@PostMapping
	public ResponseEntity<?> createShow(@Valid @RequestBody CreateShowRequest request){
		return showService.createShow(request);
		
	}
	@GetMapping
	public ResponseEntity<?> getAllShows(){
		return showService.getAllShows();
	}
	
	@GetMapping("/{showId}")
	public ResponseEntity<?> getShowById(@PathVariable Long showId){
		return showService.getShowById(showId);
	}
	@GetMapping("/screen/{screenId}")
    public List<ShowResponse> getShowsByScreen(
            @PathVariable Long screenId) {

        return showService.getShowsByScreen(screenId);
    }
	
	@PutMapping("/{showId}")
	public ResponseEntity<?> updateShow(@PathVariable Long showId, @Valid @RequestBody CreateShowRequest request){
		return showService.updateShow(showId, request);
	}
	
	@DeleteMapping("/{showId}")
	public ResponseEntity<?> deleteShow(@PathVariable Long showId){
		return showService.deleteShow(showId);
	}
	

}
