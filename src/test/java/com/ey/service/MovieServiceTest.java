package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ey.dto.request.CreateMovieRequest;
import com.ey.dto.response.MovieResponse;
import com.ey.entity.Movie;
import com.ey.exception.MovieNotFoundException;
import com.ey.repository.MovieRepository;

public class MovieServiceTest {
	
	@Mock
	private MovieRepository movieRepository;
	
	@InjectMocks
	private MovieService movieService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createMovie_success() {
		CreateMovieRequest request = new CreateMovieRequest();
		request.setTitle("Avatar 3");
		request.setLanguage("English");
		request.setDurationMins(180);
		request.setGenre("SCI-FI");
		request.setCertificate("U/A");
		request.setReleaseDate(java.time.LocalDate.of(2025, 12, 25));
		
		Movie savedMovie = new Movie();
		savedMovie.setMovieId(1L);
		
		savedMovie.setTitle("Avatar 3");
		savedMovie.setLanguage("English");
		savedMovie.setDurationMins(180);
		savedMovie.setGenre("SCI_FI");
		savedMovie.setCertificate("U/A");
		savedMovie.setReleaseDate(request.getReleaseDate());
		savedMovie.setActive(true);
		
		when(movieRepository.save(any(Movie.class))).thenReturn(savedMovie);
		
		var response = movieService.createMovie(request);
		
		assertEquals(201, response.getStatusCodeValue());
		MovieResponse body = (MovieResponse) response.getBody();
		assertNotNull(body);
		assertEquals("Avatar 3",body.getTitle());
		assertEquals(true,body.getActive());
		
		verify(movieRepository, times(1)).save(any(Movie.class));
	}
	@Test
	void getMovieById_success() {
		
//		Arrange
		
		Long movieId=  1L;
		
		Movie movie = new Movie();
		
		movie.setMovieId(movieId);
		movie.setTitle("Akhanda");
		movie.setLanguage("Telugu");
		movie.setDurationMins(134);
		movie.setGenre("Action");
		movie.setCertificate("U/A");
		movie.setReleaseDate(java.time.LocalDate.of(2025, 12, 15));
		movie.setActive(true);
		
		when(movieRepository.findById(movieId)).thenReturn(java.util.Optional.of(movie));
		
//		Act
		var response = movieService.getMovieById(movieId);
		
//		Assert
		assertEquals(200, response.getStatusCodeValue());
		
		MovieResponse body = (MovieResponse) response.getBody();
		assertNotNull(body);
		assertEquals(movieId, body.getMovieId());
		assertEquals("Akhanda",body.getTitle());
		
		verify(movieRepository,times(1)).findById(movieId);
		
	}
	
	@Test
	void getMovieById_notFound() {
//		Arrange
		Long movieId = 99L;
		
		when(movieRepository.findById(movieId)).thenReturn(java.util.Optional.empty());
		
//		Act and Assert
		MovieNotFoundException exception = assertThrows(MovieNotFoundException.class,
				()->movieService.getMovieById(movieId));
		assertEquals("Movie Not Found with Id: "+movieId,exception.getMessage());
		
		verify(movieRepository,times(1)).findById(movieId);
	}
	
	@Test
	void deleteMovie_success() {
		Long movieId = 1L;
		
		Movie movie = new Movie();
		movie.setMovieId(movieId);
		movie.setTitle("Pushpa");
		
		when(movieRepository.findById(movieId)).thenReturn(java.util.Optional.of(movie));
		
		doNothing().when(movieRepository).delete(movie);
		
		var response = movieService.deleteMovie(movieId);
		
		assertEquals(200,response.getStatusCodeValue());
		assertEquals("Movie Deleted Successfully",response.getBody());
		
		verify(movieRepository,times(1)).findById(movieId);
		verify(movieRepository,times(1)).delete(movie);
	}

}
