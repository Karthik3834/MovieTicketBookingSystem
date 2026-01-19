package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateShowRequest;
import com.ey.dto.response.ShowResponse;
import com.ey.entity.Movie;
import com.ey.entity.MovieShow;
import com.ey.entity.Screen;
import com.ey.exception.ShowNotFoundException;
import com.ey.mapper.ShowMapper;
import com.ey.repository.MovieRepository;
import com.ey.repository.ScreenRepository;
import com.ey.repository.ShowRepository;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;


    public ResponseEntity<?> createShow(CreateShowRequest request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movie not found with id: " + request.getMovieId()));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Screen not found with id: " + request.getScreenId()));

        List<MovieShow> existingShows =
                showRepository.findByScreen_ScreenIdAndShowDate(
                        screen.getScreenId(),
                        request.getShowDate());

        boolean clash = existingShows.stream()
                .anyMatch(show ->
                        show.getStartTime().equals(request.getStartTime()));

        if (clash) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Show already exists for this screen at the given date and time");
        }

        MovieShow show = ShowMapper.toEntity(request, movie, screen);
        show.setActive(true);

        MovieShow savedShow = showRepository.save(show);

        return new ResponseEntity<>(
                ShowMapper.toResponse(savedShow),
                HttpStatus.CREATED);
    }


    public ResponseEntity<?> getAllShows() {

        List<MovieShow> shows = showRepository.findAll();

        if (shows.isEmpty()) {
            return ResponseEntity.ok("No shows available");
        }

        List<ShowResponse> responses = shows.stream()
                .map(ShowMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }


    public ResponseEntity<?> getShowById(Long showId) {

        MovieShow show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + showId));

        return ResponseEntity.ok(ShowMapper.toResponse(show));
    }


    public ResponseEntity<?> updateShow(Long showId, CreateShowRequest request) {

        MovieShow existingShow = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + showId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movie not found with id: " + request.getMovieId()));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Screen not found with id: " + request.getScreenId()));

        existingShow.setMovie(movie);
        existingShow.setScreen(screen);
        existingShow.setShowDate(request.getShowDate());
        existingShow.setStartTime(request.getStartTime());

        MovieShow updatedShow = showRepository.save(existingShow);

        return ResponseEntity.ok(ShowMapper.toResponse(updatedShow));
    }

 
    public ResponseEntity<?> deleteShow(Long showId) {

        MovieShow show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + showId));

        showRepository.delete(show);

        return ResponseEntity.ok("Show deleted successfully");
    }
}
