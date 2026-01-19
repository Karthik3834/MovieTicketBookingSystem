package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.entity.Seat;
import com.ey.entity.ShowSeat;
import com.ey.repository.SeatRepository;
import com.ey.repository.ShowSeatRepository;

@Service
public class ShowSeatService {
	
	@Autowired
	private ShowSeatRepository showSeatRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	public ResponseEntity<?> generateShowSeats(Long showId, Long screenId){
		if(showSeatRepository.existsByShowId(showId)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Show seats already generated for showId: "+showId);
		}
		List<Seat> seats = seatRepository.findByScreenId(screenId);
		
		if(seats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No seats found for screenId: "+screenId);
		}
		
		List<ShowSeat> showSeats = new ArrayList<>();
		for(Seat seat : seats) {
			ShowSeat showSeat = new ShowSeat();
			showSeat.setShowId(showId);
			showSeat.setSeatId(seat.getSeatId());
			showSeat.setStatus("AVAILABLE");
			
			showSeats.add(showSeat);
		}
		showSeatRepository.saveAll(showSeats);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(showSeats.size()+" show seats generated for showId: "+showId);
	}
	
	public ResponseEntity<?> getShowSeats(Long showId){
		List<ShowSeat> showSeats = showSeatRepository.findByShowId(showId);
		
		if(showSeats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No show seats found for showId: "+showId);
		}
		return ResponseEntity.ok(showSeats);
	}
	
	public ResponseEntity<?> getAvailableShowSeats(Long showId){
		List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(showId, "AVAILABLE");
		
		if(availableSeats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No available seats for showId: "+showId);
		}
		return ResponseEntity.ok(availableSeats);
	}

}
