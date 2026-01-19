package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateScreenRequest;
import com.ey.dto.response.ScreenResponse;
import com.ey.entity.Screen;
import com.ey.exception.ScreenNotFoundException;
import com.ey.mapper.ScreenMapper;
import com.ey.repository.ScreenRepository;
import com.ey.repository.SeatRepository;

@Service

public class ScreenService {

	@Autowired

	private ScreenRepository screenRepository;

	@Autowired

	private SeatRepository seatRepository;

	public ResponseEntity<?> createScreen(CreateScreenRequest request) {

		Screen screen = ScreenMapper.toEntity(request);

		screen.setActive(true);

		Screen saved = screenRepository.save(screen);

		ScreenResponse resp = ScreenMapper.toResponse(saved);

		resp.setCapacity((int) seatRepository.countByScreenId(saved.getScreenId()));

		return new ResponseEntity<>(resp, HttpStatus.CREATED);

	}

	public ResponseEntity<?> getAllScreens() {

		List<Screen> screens = screenRepository.findAll();

		if (screens.isEmpty())
			return ResponseEntity.ok("No screens available");

		List<ScreenResponse> list = screens.stream().map(s -> {

			ScreenResponse r = ScreenMapper.toResponse(s);

			r.setCapacity((int) seatRepository.countByScreenId(s.getScreenId()));

			return r;

		}).toList();

		return ResponseEntity.ok(list);

	}

	public ResponseEntity<?> getScreenById(Long screenId) {

		Screen s = screenRepository.findById(screenId)

				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		ScreenResponse r = ScreenMapper.toResponse(s);

		r.setCapacity((int) seatRepository.countByScreenId(screenId));

		return ResponseEntity.ok(r);

	}

	public ResponseEntity<?> updateScreen(Long screenId, CreateScreenRequest request) {

		Screen s = screenRepository.findById(screenId)

				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		s.setTheatreId(request.getTheatreId());

		s.setName(request.getName());

		Screen updated = screenRepository.save(s);

		ScreenResponse r = ScreenMapper.toResponse(updated);

		r.setCapacity((int) seatRepository.countByScreenId(screenId));

		return ResponseEntity.ok(r);

	}

	public ResponseEntity<?> deleteScreen(Long screenId) {

		Screen s = screenRepository.findById(screenId)

				.orElseThrow(() -> new ScreenNotFoundException("Screen not found with id: " + screenId));

		screenRepository.delete(s);

		return ResponseEntity.ok("Screen deleted successfully");

	}

}