package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateEntryRequest;
import com.ey.dto.response.EntryResponse;
import com.ey.entity.Booking;
import com.ey.entity.Entry;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.EntryMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.EntryRepository;

import jakarta.transaction.Transactional;

@Service
public class EntryService {

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Transactional
	public ResponseEntity<?> markEntry(CreateEntryRequest request) {

		Booking booking = bookingRepository.findById(request.getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + request.getBookingId()));

		if (!"BOOKED".equals(booking.getBookingStatus())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Entry not allowed. Booking status is: " + booking.getBookingStatus());
		}

		if (entryRepository.existsByBooking_BookingId(booking.getBookingId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Entry already marked for bookingId: " + booking.getBookingId());
		}

		Entry entry = EntryMapper.toEntity(request, booking);
		Entry savedEntry = entryRepository.save(entry);

		EntryResponse response = EntryMapper.toResponse(savedEntry);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
