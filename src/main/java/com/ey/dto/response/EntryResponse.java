package com.ey.dto.response;

import java.time.LocalDateTime;

public class EntryResponse {
	
	private Long entryId;
	private Long bookingId;
	private String bookingStatus;
	private LocalDateTime enteredAt;
	private String enteredBy;
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public LocalDateTime getEnteredAt() {
		return enteredAt;
	}
	public void setEnteredAt(LocalDateTime enteredAt) {
		this.enteredAt = enteredAt;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	
	

}
