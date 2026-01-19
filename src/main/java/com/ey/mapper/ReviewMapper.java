package com.ey.mapper;

import java.time.LocalDateTime;

import com.ey.dto.request.CreateReviewRequest;
import com.ey.dto.response.ReviewResponse;
import com.ey.entity.Movie;
import com.ey.entity.Review;
import com.ey.entity.User;

public class ReviewMapper {

	public static Review toEntity(CreateReviewRequest request, User user, Movie movie) {

		Review review = new Review();
		review.setUser(user);
		review.setMovie(movie);
		review.setRating(request.getRating());
		review.setComment(request.getComment());
		review.setCreatedAt(LocalDateTime.now());

		return review;
	}

	public static ReviewResponse toResponse(Review review) {

		ReviewResponse response = new ReviewResponse();
		response.setReviewId(review.getReviewId());
		response.setUserId(review.getUser().getUserId());
		response.setMovieId(review.getMovie().getMovieId());
		response.setRating(review.getRating());
		response.setComment(review.getComment());
		response.setCreatedAt(review.getCreatedAt());

		return response;
	}
}
