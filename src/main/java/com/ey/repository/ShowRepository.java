package com.ey.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.MovieShow;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow, Long>{
	
	List<MovieShow> findByScreen_ScreenIdAndShowDate(Long screenId, LocalDate showDate);
	
	List<MovieShow> findByMovie_MovieId(Long movieId);
	
	List<MovieShow> findByScreen_ScreenId(Long screenId);
	
	List<MovieShow> findByMovie_MovieIdAndScreen_Theatre_TheatreId(Long movieId, Long theatreId);

}
