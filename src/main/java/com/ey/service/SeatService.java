package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.entity.Screen;
import com.ey.entity.Seat;
import com.ey.repository.ScreenRepository;
import com.ey.repository.SeatRepository;

@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ScreenRepository screenRepository;

	public ResponseEntity<?> generateSeatsForScreen(Long screenId) {

		if (seatRepository.existsByScreen_ScreenId(screenId)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Seats already generated for screenId: " + screenId);
		}

		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new RuntimeException("Screen not found with id: " + screenId));

		List<Seat> seats = new ArrayList<>();
		char[] rows = { 'A', 'B', 'C', 'D' };

		for (char row : rows) {
			for (int seatNumber = 1; seatNumber <= 10; seatNumber++) {

				Seat seat = new Seat();
				seat.setScreen(screen);
				seat.setRowLabel(String.valueOf(row));
				seat.setSeatNumber(seatNumber);
				seat.setSeatTag(row + String.valueOf(seatNumber));

				seats.add(seat);
			}
		}

		seatRepository.saveAll(seats);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(seats.size() + " seats generated for screenId: " + screenId);
	}

	public ResponseEntity<?> getSeatsByScreenId(Long screenId) {

		List<Seat> seats = seatRepository.findByScreen_ScreenId(screenId);

		if (seats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No seats found for screenId: " + screenId);
		}

		return ResponseEntity.ok(seats);
	}

}
