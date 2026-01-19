package com.ey.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {
	
	private Long bookingId;
	private Long userId;
	private Long showId;
	private String bookingStatus;
	private LocalDateTime createdAt;
	private List<Long> showSeatIds;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
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
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<Long> getShowSeatIds() {
		return showSeatIds;
	}
	public void setShowSeatIds(List<Long> showSeatIds) {
		this.showSeatIds = showSeatIds;
	}
	
	

}
