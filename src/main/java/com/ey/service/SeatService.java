package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.entity.Seat;
import com.ey.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	public ResponseEntity<?> generateSeatsForScreen(Long screenId){
		if(seatRepository.existsByScreenId(screenId)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Seats are already generated for screenId: "+screenId);
		}
		List<Seat> seats = new ArrayList<>();
		char[] rows = {'A','B','C','D'};
		for(char row : rows) {
			for(int seatNumber = 1;seatNumber<=10;seatNumber++) {
				Seat seat = new Seat();
				seat.setScreenId(screenId);
				seat.setRowLabel(String.valueOf(row));
				seat.setSeatNumber(seatNumber);
				seat.setSeatTag(row+String.valueOf(seatNumber));
				seats.add(seat);
			}
		}
		seatRepository.saveAll(seats);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(seats.size()+" seats generated for screenId");
	}
	
	public ResponseEntity<?> getSeatsByScreenId(Long screenId){
		List<Seat> seats = seatRepository.findByScreenId(screenId);
		
		if(seats.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No seats found for screenId: "+screenId);
		}
		return ResponseEntity.ok(seats);
	}
	

}
