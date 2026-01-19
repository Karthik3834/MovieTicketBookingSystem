package com.ey.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MovieShow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	
//	private Long screenId;
//	
//	private Long movieId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id",nullable = false)
	private Screen screen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id",nullable = false)
	private Movie movie;
	
	private LocalDate showDate;
	
	private LocalTime startTime;
	
	private Boolean active;

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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
