package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreatePaymentRequest {
	
	
	@NotNull(message = "Booking is required")
	private Long bookingId;
	
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be greater than zero")
	private Double amount;
	@NotBlank(message = "Payment method is required")
	private String paymentMethod;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymenMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
