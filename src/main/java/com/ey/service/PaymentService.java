package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreatePaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Booking;
import com.ey.entity.BookingSeat;
import com.ey.entity.Payment;
import com.ey.entity.ShowSeat;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.PaymentMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.BookingSeatRepository;
import com.ey.repository.PaymentRepository;
import com.ey.repository.ShowSeatRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingSeatRepository bookingSeatRepository;

	@Autowired
	private ShowSeatRepository showSeatRepository;

	@Transactional
	public ResponseEntity<?> processPayment(CreatePaymentRequest request) {

		Booking booking = bookingRepository.findById(request.getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + request.getBookingId()));

		Payment payment = PaymentMapper.toEntity(request);

		boolean paymentSuccess = request.getAmount() > 0;

		if (paymentSuccess) {

			payment.setPaymentStatus("SUCCESS");
			booking.setBookingStatus("BOOKED");

			paymentRepository.save(payment);
			bookingRepository.save(booking);

		} else {

			payment.setPaymentStatus("FAILED");
			booking.setBookingStatus("CANCELLED");

			paymentRepository.save(payment);
			bookingRepository.save(booking);

			List<BookingSeat> bookingSeats = bookingSeatRepository.findByBooking_BookingId(booking.getBookingId());

			for (BookingSeat bookingSeat : bookingSeats) {

				ShowSeat showSeat = bookingSeat.getShowSeat();
				showSeat.setStatus("AVAILABLE");

				showSeatRepository.save(showSeat);
				bookingSeatRepository.delete(bookingSeat);
			}
		}

		PaymentResponse response = PaymentMapper.toResponse(payment, booking.getBookingStatus());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
