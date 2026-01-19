package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;

public class CancelBookingRequest {
	
	@NotNull(message = "Booking Id is required")
	private Long bookingId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
	

}
