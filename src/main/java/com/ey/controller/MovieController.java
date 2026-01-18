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

import com.ey.dto.request.CreateMovieRequest;
import com.ey.dto.response.MovieResponse;
import com.ey.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody CreateMovieRequest request){
		return movieService.createMovie(request);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long movieId){
		return movieService.getMovieById(movieId);
	}
	
	@PutMapping("/{movieId}")
	public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long movieId, @RequestBody CreateMovieRequest request){
		return movieService.updateMovie(movieId, request);
	}
	
	@DeleteMapping("/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long movieId){
		return movieService.deleteMovie(movieId);
	}
	

}
