package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.CreateMovieRequest;
import com.ey.dto.response.MovieResponse;
import com.ey.entity.Movie;

@Component
public class MovieMapper {
	
	public static Movie toEntity(CreateMovieRequest request) {
		Movie movie  = new Movie();
		movie.setTitle(request.getTitle());
		movie.setLanguage(request.getLanguage());
		movie.setDurationMins(request.getDurationMins());
		movie.setGenre(request.getGenre());
		movie.setCertificate(request.getCertificate());
		movie.setReleaseDate(request.getReleaseDate());
		return movie;
	}
	public static MovieResponse toResponse(Movie movie) {
		MovieResponse response = new MovieResponse();
		response.setMovieId(movie.getMovieId());
		response.setTitle(movie.getTitle());
		response.setLanguage(movie.getLanguage());
		response.setDurationMins(movie.getDurationMins());
		response.setGenre(movie.getGenre());
		response.setCertificate(movie.getCertificate());
		response.setReleaseDate(movie.getReleaseDate());
		response.setActive(movie.getActive());
		return response;
	}

}
