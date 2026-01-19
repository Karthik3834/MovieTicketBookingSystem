package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateReviewRequest;
import com.ey.dto.response.ReviewResponse;
import com.ey.entity.Movie;
import com.ey.entity.Review;
import com.ey.entity.User;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.ReviewMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.EntryRepository;
import com.ey.repository.MovieRepository;
import com.ey.repository.ReviewRepository;
import com.ey.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private EntryRepository entryRepository;

	@Transactional
	public ResponseEntity<?> createReview(Long userId, CreateReviewRequest request) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

		Movie movie = movieRepository.findById(request.getMovieId())
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + request.getMovieId()));

		if (reviewRepository.findByUser_UserIdAndMovie_MovieId(userId, movie.getMovieId()).isPresent()) {

			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("User has already reviewed this movie");
		}

		boolean hasValidBooking = bookingRepository.findByUser_UserId(userId).stream()
				.filter(b -> "BOOKED".equals(b.getBookingStatus()))
				.anyMatch(b -> b.getShow().getMovie().getMovieId()
						.equals(movie.getMovieId()));

		if (!hasValidBooking) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("User has no valid booking for this movie");
		}

		boolean hasEntered = bookingRepository.findByUser_UserId(userId).stream()
				.filter(b -> "BOOKED".equals(b.getBookingStatus()))
				.anyMatch(b -> entryRepository.findByBooking_BookingId(b.getBookingId())
						.isPresent());

		if (!hasEntered) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("User has not entered theatre for this movie");
		}

		Review review = ReviewMapper.toEntity(request, user, movie);

		Review savedReview = reviewRepository.save(review);

		ReviewResponse response = ReviewMapper.toResponse(savedReview);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
