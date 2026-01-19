package com.ey.mapper;

import java.time.LocalDateTime;

import com.ey.dto.request.CreatePaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Payment;

public class PaymentMapper {
	
	public static Payment toEntity(CreatePaymentRequest request) {
		Payment payment = new Payment();
		payment.setBookingId(request.getBookingId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setCreatedAt(LocalDateTime.now());
		return payment;
	}
	
	public static PaymentResponse toResponse(Payment payment, String bookingStatus) {
		PaymentResponse response = new PaymentResponse();
		
		response.setPaymentId(payment.getPaymentId());
		response.setBookingId(payment.getBookingId());
		response.setAmount(payment.getAmount());
		response.setPaymentMethod(payment.getPaymentMethod());
		response.setPaymentStatus(payment.getPaymentStatus());
		response.setBookingStatus(bookingStatus);
		response.setCreatedAt(payment.getCreatedAt());
		return response;
	}

}
