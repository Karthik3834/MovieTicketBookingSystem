package com.ey.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.MovieShow;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow, Long>{
	List<MovieShow> findByScreenIdAndShowDate(Long screenId, LocalDate showDate);
	
	List<MovieShow> findByMovieId(Long movieId);
	
	List<MovieShow> findByScreenId(Long screenId);

}
