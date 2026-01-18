package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateTheatreRequest;
import com.ey.dto.response.TheatreResponse;
import com.ey.entity.Theatre;
import com.ey.exception.TheatreNotFoundException;
import com.ey.mapper.TheatreMapper;
import com.ey.repository.TheatreRepository;

import jakarta.validation.Valid;

@Service
public class TheatreService {
	
	@Autowired
	private TheatreRepository theatreRepository;

	public ResponseEntity<TheatreResponse> createTheatre(@Valid CreateTheatreRequest request) {
		Theatre theatre = TheatreMapper.toEntity(request);
		theatre.setActive(true);
		Theatre savedTheatre = theatreRepository.save(theatre);
		TheatreResponse response = TheatreMapper.toResponse(savedTheatre);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAllTheatres() {
		List<Theatre> theatres = theatreRepository.findAll();
		if(theatres.isEmpty()) {
			return ResponseEntity.ok("No theatres available");
		}
		List<TheatreResponse> responseList = theatres.stream()
				.map(TheatreMapper::toResponse)
				.collect(Collectors.toList());
		return ResponseEntity.ok(responseList);
	}

	public ResponseEntity<TheatreResponse> getTheatreById(Long theatreId) {
		Theatre theatre = theatreRepository.findById(theatreId)
				.orElseThrow(()-> new TheatreNotFoundException("Theatre Not Found with Id: "+theatreId));
		return ResponseEntity.ok(TheatreMapper.toResponse(theatre));
	}

	public ResponseEntity<TheatreResponse> updateTheatre(Long theatreId, @Valid CreateTheatreRequest request) {
		Theatre theatre = theatreRepository.findById(theatreId)
				.orElseThrow(()-> new TheatreNotFoundException("Theatre Not Found with Id: "+theatreId));
		theatre.setName(request.getName());
		theatre.setLocation(request.getLocation());
		theatre.setAddress(request.getAddress());
		theatre.setNoOfScreens(request.getNoOfScreens());
		
		Theatre updatedTheatre = theatreRepository.save(theatre);
		return ResponseEntity.ok(TheatreMapper.toResponse(updatedTheatre));
	}

	public ResponseEntity<?> deleteTheatre(Long theatreId) {
		Theatre theatre = theatreRepository.findById(theatreId)
				.orElseThrow(()-> new TheatreNotFoundException("Theatre Not Found with Id: "+theatreId));
		
		theatreRepository.delete(theatre);
		return ResponseEntity.ok("Theatre deleted successfully");
	}
	

}
