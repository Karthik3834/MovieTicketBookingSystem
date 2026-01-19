package com.ey.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {
	
	@NotNull(message = "User id is required")
	private Long userId;
	
	@NotNull(message = "Show Id is required")
	private Long showId;
	
	@NotEmpty(message = "Atleast one seat must be selected")
	private List<Long> showSeatIds;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public List<Long> getShowSeatIds() {
		return showSeatIds;
	}

	public void setShowSeatIds(List<Long> showSeatIds) {
		this.showSeatIds = showSeatIds;
	}
	
	
	

}
