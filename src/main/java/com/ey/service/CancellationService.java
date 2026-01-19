package com.ey.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CancelBookingRequest;
import com.ey.dto.response.CancelBookingResponse;
import com.ey.entity.Booking;
import com.ey.entity.BookingSeat;
import com.ey.entity.Payment;
import com.ey.entity.Refund;
import com.ey.entity.ShowSeat;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.BookingRepository;
import com.ey.repository.BookingSeatRepository;
import com.ey.repository.EntryRepository;
import com.ey.repository.PaymentRepository;
import com.ey.repository.RefundRepository;
import com.ey.repository.ShowSeatRepository;

import jakarta.transaction.Transactional;

@Service
public class CancellationService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingSeatRepository bookingSeatRepository;

	@Autowired
	private ShowSeatRepository showSeatRepository;

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RefundRepository refundRepository;

	@Transactional
	public ResponseEntity<?> cancelBooking(CancelBookingRequest request) {

		Booking booking = bookingRepository.findById(request.getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + request.getBookingId()));

		if (entryRepository.existsByBooking_BookingId(booking.getBookingId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Booking already entered. Cancellation not allowed.");
		}

		if ("CANCELLED".equals(booking.getBookingStatus())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Booking already cancelled.");
		}

		booking.setBookingStatus("CANCELLED");
		bookingRepository.save(booking);

		List<BookingSeat> bookingSeats = bookingSeatRepository.findByBooking_BookingId(booking.getBookingId());

		for (BookingSeat bookingSeat : bookingSeats) {

			ShowSeat showSeat = bookingSeat.getShowSeat();
			showSeat.setStatus("AVAILABLE");

			showSeatRepository.save(showSeat);
			bookingSeatRepository.delete(bookingSeat);
		}

		CancelBookingResponse response = new CancelBookingResponse();
		response.setBookingId(booking.getBookingId());
		response.setBookingStatus("CANCELLED");
		response.setCancelledAt(LocalDateTime.now());

		List<Payment> payments = paymentRepository.findByBookingId(booking.getBookingId());

		if (!payments.isEmpty() && !refundRepository.existsByBooking_BookingId(booking.getBookingId())) {

			Payment payment = payments.get(0);

			Refund refund = new Refund();
			refund.setBooking(booking);
			refund.setPayment(payment);
			refund.setAmount(payment.getAmount());
			refund.setRefundStatus("SUCCESS");
			refund.setCreatedAt(LocalDateTime.now());

			Refund savedRefund = refundRepository.save(refund);

			response.setRefundInitiated(true);
			response.setRefundId(savedRefund.getRefundId());
			response.setRefundAmount(savedRefund.getAmount());
		} else {
			response.setRefundInitiated(false);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
