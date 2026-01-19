package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateMovieRequest;
import com.ey.dto.response.MovieResponse;
import com.ey.dto.response.ShowSummaryResponse;
import com.ey.dto.response.TheatreSummaryResponse;
import com.ey.entity.Movie;
import com.ey.entity.MovieShow;
import com.ey.exception.MovieNotFoundException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.MovieMapper;
import com.ey.repository.MovieRepository;
import com.ey.repository.ShowRepository;

import jakarta.validation.Valid;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieMapper movieMapper;

	public ResponseEntity<MovieResponse> createMovie(@Valid CreateMovieRequest request) {
		Movie movie = movieMapper.toEntity(request);
		movie.setActive(true);
		Movie savedMovie = movieRepository.save(movie);
		MovieResponse response = movieMapper.toResponse(savedMovie);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		
		if(movies.isEmpty()) {
			return ResponseEntity.ok("No Movies Available");
		}
		List<MovieResponse> responseList = movies.stream()
				.map(MovieMapper::toResponse)
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(responseList);
	}

	public ResponseEntity<MovieResponse> getMovieById(Long movieId) {
		Movie movie  = movieRepository.findById(movieId)
				.orElseThrow(()->new MovieNotFoundException("Movie Not Found with Id: "+movieId));
		return ResponseEntity.ok(MovieMapper.toResponse(movie));
	}

	public ResponseEntity<MovieResponse> updateMovie(Long movieId, CreateMovieRequest request) {
		Movie movie  = movieRepository.findById(movieId)
				.orElseThrow(()->new MovieNotFoundException("Movie Not Found with Id: "+movieId));
		movie.setTitle(request.getTitle());
		movie.setLanguage(request.getLanguage());
		movie.setDurationMins(request.getDurationMins());
		movie.setGenre(request.getGenre());
		movie.setCertificate(request.getCertificate());
		movie.setReleaseDate(request.getReleaseDate());
		
		Movie updatedMovie = movieRepository.save(movie);
		
		return ResponseEntity.ok(MovieMapper.toResponse(updatedMovie));
	}

	public ResponseEntity<?> deleteMovie(Long movieId) {
		Movie movie  = movieRepository.findById(movieId)
				.orElseThrow(()->new MovieNotFoundException("Movie Not Found with Id: "+movieId));
		movieRepository.delete(movie);
		return ResponseEntity.ok("Movie Deleted Successfully");
	}
	public List<TheatreSummaryResponse> getTheatresByMovie(Long movieId) {

		List<MovieShow> shows = showRepository.findByMovie_MovieId(movieId);

		if (shows.isEmpty()) {
			throw new ResourceNotFoundException("No theatres found for movieId: " + movieId);
		}

		return shows.stream().map(show -> show.getScreen().getTheatre()).distinct().map(
				theatre -> new TheatreSummaryResponse(theatre.getTheatreId(), theatre.getName(), theatre.getLocation()))
				.toList();
	}

	public List<ShowSummaryResponse> getShowsByMovieAndTheatre(Long movieId, Long theatreId) {

		List<MovieShow> shows = showRepository.findByMovie_MovieIdAndScreen_Theatre_TheatreId(movieId, theatreId);

		if (shows.isEmpty()) {
			throw new ResourceNotFoundException("No shows found for movieId " + movieId + " in theatreId " + theatreId);
		}

		return shows.stream().map(show -> new ShowSummaryResponse(show.getShowId(), show.getShowDate(),
				show.getStartTime(), show.getScreen().getName())).toList();
	}

}
