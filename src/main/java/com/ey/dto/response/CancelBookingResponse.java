package com.ey.dto.response;

import java.time.LocalDateTime;

public class CancelBookingResponse {
	
	private Long bookingId;
	
	private String bookingStatus;
	
	private Boolean refundInitiated;
	
	private Long refundId;
	
	private Double refundAmount;
	private LocalDateTime cancelledAt;
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
	public Boolean getRefundInitiated() {
		return refundInitiated;
	}
	public void setRefundInitiated(Boolean refundInitiated) {
		this.refundInitiated = refundInitiated;
	}
	public Long getRefundId() {
		return refundId;
	}
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public LocalDateTime getCancelledAt() {
		return cancelledAt;
	}
	public void setCancelledAt(LocalDateTime cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
	
	

}
