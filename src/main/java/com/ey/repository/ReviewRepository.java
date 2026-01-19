package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findByMovie_MovieId(Long movieId);
	Optional<Review> findByUser_UserIdAndMovie_MovieId(Long userId, Long movieId);
	
	List<Review> findByUser_UserId(Long userId);

}
