package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEntryRequest {
	
	@NotNull(message= "Booking Id is required")
	private Long bookingId;
	
	@NotBlank(message = "Entered by (staff identifier) is required")
	private String enteredBy;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	
	

}
