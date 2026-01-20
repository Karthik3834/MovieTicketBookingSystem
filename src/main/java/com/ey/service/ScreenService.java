package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateScreenRequest;
import com.ey.dto.response.ScreenResponse;
import com.ey.entity.Screen;
import com.ey.entity.Theatre;
import com.ey.exception.ResourceNotFoundException;
import com.ey.exception.ScreenNotFoundException;
import com.ey.mapper.ScreenMapper;
import com.ey.repository.ScreenRepository;
import com.ey.repository.SeatRepository;
import com.ey.repository.TheatreRepository;

@Service
public class ScreenService {

	@Autowired
	private ScreenRepository screenRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private SeatRepository seatRepository;

	public ResponseEntity<?> createScreen(CreateScreenRequest request) {

		Theatre theatre = theatreRepository.findById(request.getTheatreId())
				.orElseThrow(() -> new RuntimeException("Theatre not found with id: " + request.getTheatreId()));

		Screen screen = ScreenMapper.toEntity(request, theatre);
		screen.setActive(true);

		Screen savedScreen = screenRepository.save(screen);

		ScreenResponse response = ScreenMapper.toResponse(savedScreen);
		response.setCapacity((int) seatRepository.countByScreen_ScreenId(savedScreen.getScreenId()));

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAllScreens() {

		List<Screen> screens = screenRepository.findAll();

		if (screens.isEmpty()) {
			return ResponseEntity.ok("No screens available");
		}

		List<ScreenResponse> responses = screens.stream().map(screen -> {
			ScreenResponse r = ScreenMapper.toResponse(screen);
			r.setCapacity((int) seatRepository.countByScreen_ScreenId(screen.getScreenId()));
			return r;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(responses);
	}

	public ResponseEntity<?> getScreenById(Long screenId) {

		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		ScreenResponse response = ScreenMapper.toResponse(screen);
		response.setCapacity((int) seatRepository.countByScreen_ScreenId(screenId));

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> updateScreen(Long screenId, CreateScreenRequest request) {

		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		Theatre theatre = theatreRepository.findById(request.getTheatreId())
				.orElseThrow(() -> new RuntimeException("Theatre not found with id: " + request.getTheatreId()));

		screen.setTheatre(theatre);
		screen.setName(request.getName());

		Screen updated = screenRepository.save(screen);

		ScreenResponse response = ScreenMapper.toResponse(updated);
		response.setCapacity((int) seatRepository.countByScreen_ScreenId(screenId));

		return ResponseEntity.ok(response);
	}


	public ResponseEntity<?> deleteScreen(Long screenId) {

		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		screenRepository.delete(screen);

		return ResponseEntity.ok("Screen deleted successfully");
	}

	public List<ScreenResponse> getScreensByTheatre(Long theatreId) {

		List<Screen> screens = screenRepository.findByTheatre_TheatreId(theatreId);

		if (screens.isEmpty()) {
			throw new ResourceNotFoundException("No screens found for theatreId: " + theatreId);
		}

		return screens.stream().map(ScreenMapper::toResponse).toList();
	}
}