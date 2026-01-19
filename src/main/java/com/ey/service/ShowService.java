package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateShowRequest;
import com.ey.dto.response.ShowResponse;
import com.ey.entity.MovieShow;
import com.ey.exception.ShowNotFoundException;
import com.ey.mapper.ShowMapper;
import com.ey.repository.ShowRepository;

@Service
public class ShowService {
	
	@Autowired
	private ShowRepository showRepository;
	
	public ResponseEntity<?> createShow(CreateShowRequest request){
		
		List<MovieShow> existingShows = showRepository.findByScreenIdAndShowDate(request.getScreenId(), request.getShowDate());
		
		boolean clash  = existingShows.stream()
				.anyMatch(s->s.getStartTime()
						.equals(request.getStartTime()));
		
		if(clash) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Show already exists for this screen at the given date and time");
			
		}
		MovieShow show = ShowMapper.toEntity(request);
		show.setActive(true);
		MovieShow savedShow = showRepository.save(show);
		ShowResponse response = ShowMapper.toResponse(savedShow);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<?> getAllShows(){
		List<MovieShow> shows = showRepository.findAll();
		if(shows.isEmpty()) {
			return ResponseEntity.ok("No shows available");
		}
		List<ShowResponse> responseList = shows.stream()
				.map(ShowMapper::toResponse)
				.collect(Collectors.toList());
		return ResponseEntity.ok(responseList);
	}
	
	public ResponseEntity<?> getShowById(Long showId){
		MovieShow show = showRepository.findById(showId)
				.orElseThrow(()->new ShowNotFoundException("Show not found with id: "+showId));
		return ResponseEntity.ok(ShowMapper.toResponse(show));
	}
	
	public ResponseEntity<?> updateShow(Long showId, CreateShowRequest request){
		MovieShow show = showRepository.findById(showId)
				.orElseThrow(()->new ShowNotFoundException("Show not found with id: "+showId));
		
		show.setScreenId(request.getScreenId());
		show.setMovieId(request.getMovieId());
		show.setShowDate(request.getShowDate());
		show.setStartTime(request.getStartTime());
		
		MovieShow updatedShow = showRepository.save(show);
		return ResponseEntity.ok(ShowMapper.toResponse(updatedShow));
	}
	
	public ResponseEntity<?> deleteShow(Long showId){
		MovieShow show = showRepository.findById(showId)
				.orElseThrow(()->new ShowNotFoundException("Show not found with id: "+showId));
		
		showRepository.delete(show);
		return ResponseEntity.ok("Show deleted successfully");
	}

}
