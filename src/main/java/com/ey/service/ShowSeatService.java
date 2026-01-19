package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.response.ShowSeatResponse;
import com.ey.entity.MovieShow;
import com.ey.entity.Seat;
import com.ey.entity.ShowSeat;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.ShowSeatMapper;
import com.ey.repository.SeatRepository;
import com.ey.repository.ShowRepository;
import com.ey.repository.ShowSeatRepository;

import jakarta.transaction.Transactional;

@Service
public class ShowSeatService {

	@Autowired
	private ShowSeatRepository showSeatRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ShowRepository showRepository;

	@Transactional
	public ResponseEntity<?> generateShowSeats(Long showId, Long screenId) {

		if (showSeatRepository.existsByShow_ShowId(showId)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Show seats already generated for showId: " + showId);
		}

		MovieShow show = showRepository.findById(showId)
				.orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));

		List<Seat> seats = seatRepository.findByScreen_ScreenId(screenId);

		if (seats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No seats found for screenId: " + screenId);
		}

		for (Seat seat : seats) {

			ShowSeat showSeat = new ShowSeat();
			showSeat.setShow(show);
			showSeat.setSeat(seat);
			showSeat.setStatus("AVAILABLE");

			showSeatRepository.save(showSeat);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Show seats generated for showId: " + showId);
	}

	public ResponseEntity<List<ShowSeatResponse>> getShowSeats(Long showId) {

		List<ShowSeat> showSeats = showSeatRepository.findByShow_ShowId(showId);

		List<ShowSeatResponse> response = showSeats.stream().map(ShowSeatMapper::toResponse).toList();

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<List<ShowSeatResponse>> getAvailableShowSeats(Long showId) {

		List<ShowSeat> showSeats = showSeatRepository.findByShow_ShowIdAndStatus(showId, "AVAILABLE");

		List<ShowSeatResponse> response = showSeats.stream().map(ShowSeatMapper::toResponse).toList();

		return ResponseEntity.ok(response);
	}
}