package com.ey.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieShow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	
	private Long screenId;
	
	private Long movieId;
	
	private LocalDate showDate;
	
	private LocalTime startTime;
	
	private Boolean active;
	
	

	public MovieShow() {
		super();
	}



	public MovieShow(Long showId, Long screenId, Long movieId, LocalDate showDate, LocalTime startTime,
			Boolean active) {
		super();
		this.showId = showId;
		this.screenId = screenId;
		this.movieId = movieId;
		this.showDate = showDate;
		this.startTime = startTime;
		this.active = active;
	}



	public Long getShowId() {
		return showId;
	}



	public void setShowId(Long showId) {
		this.showId = showId;
	}



	public Long getScreenId() {
		return screenId;
	}



	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}



	public Long getMovieId() {
		return movieId;
	}



	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}



	public LocalDate getShowDate() {
		return showDate;
	}



	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}



	public LocalTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
