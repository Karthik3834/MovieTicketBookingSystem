package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ey.dto.request.CreateTheatreRequest;
import com.ey.dto.response.TheatreResponse;
import com.ey.entity.Theatre;
import com.ey.exception.TheatreNotFoundException;
import com.ey.repository.TheatreRepository;

public class TheatreServiceTest {
	
	@Mock
	private TheatreRepository theatreRepository;
	
	@InjectMocks
	private TheatreService theatreService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	@Test
	void createTheatre_success() {
//		arrange
		
		CreateTheatreRequest request = new CreateTheatreRequest();
		request.setName("PVR Cinemas");
		request.setLocation("Hyderabad");
		request.setAddress("Madhapur");
		request.setNoOfScreens(4);
		
		Theatre savedTheatre = new Theatre();
		savedTheatre.setTheatreId(1L);
		savedTheatre.setName("PVR Cinemas");
		savedTheatre.setLocation("Hyderabad");
		savedTheatre.setAddress("Madhapur");
		savedTheatre.setNoOfScreens(4);
		savedTheatre.setActive(true);
		
		when(theatreRepository.save(any(Theatre.class))).thenReturn(savedTheatre);
		
//		act
		var response = theatreService.createTheatre(request);
//		assert
		assertEquals(201,response.getStatusCodeValue());
		
		TheatreResponse body = (TheatreResponse) response.getBody();
		assertNotNull(body);
		assertEquals("PVR Cinemas",body.getName());
		assertEquals(true, body.getActive());
		
		verify(theatreRepository,times(1)).save(any(Theatre.class));
	}
	
	@Test
	void getTheatreById_success() {
//		arrange
		Long theatreId = 1L;
		
		Theatre theatre = new Theatre();
		theatre.setTheatreId(theatreId);
		theatre.setName("PVR Cinemas");
		theatre.setLocation("Hyderabad");
		theatre.setAddress("Nexus Mall");
		theatre.setNoOfScreens(3);
		theatre.setActive(true);
		
		when(theatreRepository.findById(theatreId)).thenReturn(Optional.of(theatre));
		
//		act
		var response = theatreService.getTheatreById(theatreId);
		
//		assert
		assertEquals(200,response.getStatusCodeValue());
		
		TheatreResponse body = (TheatreResponse) response.getBody();
		assertNotNull(body);
		assertEquals(theatreId,body.getTheatreId());
		assertEquals("PVR Cinemas",body.getName());
		verify(theatreRepository,times(1)).findById(theatreId);
	}
	
	@Test
	void getTheatreById_notFound() {
		
//		arrange
		Long theatreId = 99L;
		when(theatreRepository.findById(theatreId)).thenReturn(Optional.empty());
		
//		act and assert
		TheatreNotFoundException exception = assertThrows(TheatreNotFoundException.class,
				()->theatreService.getTheatreById(theatreId));
		assertEquals("Theatre Not Found with Id: "+theatreId,exception.getMessage());
		
		verify(theatreRepository,times(1)).findById(theatreId);
	}
	
	@Test
	void deleteTheatre_success() {
//		arrange
		Long theatreId = 1L;
		Theatre theatre = new Theatre();
		theatre.setTheatreId(theatreId);
		theatre.setName("INOX");
		
		when(theatreRepository.findById(theatreId)).thenReturn(Optional.of(theatre));
		
		doNothing().when(theatreRepository).delete(theatre);
		
//		act
		var response = theatreService.deleteTheatre(theatreId);
		
//		assert
		assertEquals(200,response.getStatusCodeValue());
		assertEquals("Theatre deleted successfully",response.getBody());
		
		verify(theatreRepository,times(1)).findById(theatreId);
		verify(theatreRepository,times(1)).delete(theatre);
		}
	
	

	

}
