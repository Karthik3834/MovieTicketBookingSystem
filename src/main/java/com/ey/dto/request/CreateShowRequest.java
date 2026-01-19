package com.ey.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class CreateShowRequest {
	
	@NotNull(message = "Screen Id is required")
	private Long screenId;
	@NotNull(message = "Movie Id is required")
	private Long movieId;
	@NotNull(message = "Date is required")
	private LocalDate showDate;
	@NotNull(message = "Time  is required")
	private LocalTime startTime;
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

}
