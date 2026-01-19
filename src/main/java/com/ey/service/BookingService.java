package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateBookingRequest;
import com.ey.dto.response.BookingResponse;
import com.ey.entity.Booking;
import com.ey.entity.BookingSeat;
import com.ey.entity.MovieShow;
import com.ey.entity.ShowSeat;
import com.ey.entity.User;
import com.ey.exception.ConflictException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.BookingMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.BookingSeatRepository;
import com.ey.repository.ShowRepository;
import com.ey.repository.ShowSeatRepository;
import com.ey.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingSeatRepository bookingSeatRepository;

	@Autowired
	private ShowSeatRepository showSeatRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showRepository;

	@Transactional
	public ResponseEntity<?> createBooking(CreateBookingRequest request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

		MovieShow show = showRepository.findById(request.getShowId())
				.orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + request.getShowId()));

		Booking booking = BookingMapper.toEntity(request, user, show);

		Booking savedBooking = bookingRepository.save(booking);

		for (Long showSeatId : request.getShowSeatIds()) {

			if (bookingSeatRepository.existsByShowSeat_ShowSeatId(showSeatId)) {
				throw new ConflictException("Seat already booked for showSeatId: " + showSeatId);
			}

			ShowSeat showSeat = showSeatRepository.findById(showSeatId)
					.orElseThrow(() -> new ResourceNotFoundException("ShowSeat not found with id: " + showSeatId));

			if (!"AVAILABLE".equals(showSeat.getStatus())) {
				throw new ResourceNotFoundException("Seat not available for showSeatId: " + showSeatId);
			}

			showSeat.setStatus("BOOKED");
			showSeatRepository.save(showSeat);

			BookingSeat bookingSeat = new BookingSeat();
			bookingSeat.setBooking(savedBooking);
			bookingSeat.setShowSeat(showSeat);

			bookingSeatRepository.save(bookingSeat);
		}

		BookingResponse response = BookingMapper.toResponse(savedBooking, request.getShowSeatIds());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}