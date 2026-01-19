package com.ey.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.ey.dto.request.CreateBookingRequest;
import com.ey.dto.response.BookingResponse;
import com.ey.entity.Booking;
import com.ey.entity.MovieShow;
import com.ey.entity.User;

public class BookingMapper {
	
	public static Booking toEntity(CreateBookingRequest request, User user, MovieShow show) {
		Booking booking = new Booking();
		
		booking.setUser(user);
		booking.setShow(show);
		booking.setBookingStatus("PENDING");
		booking.setCreatedAt(LocalDateTime.now());
		return booking;
	}
	
	public static BookingResponse toResponse(Booking booking,List<Long> showSeatIds) {
		BookingResponse response = new BookingResponse();
		
		response.setBookingId(booking.getBookingId());
		response.setUserId(booking.getUser().getUserId());
		response.setShowId(booking.getShow().getShowId());
		response.setCreatedAt(booking.getCreatedAt());
		response.setBookingStatus(booking.getBookingStatus());
		response.setShowSeatIds(showSeatIds);
		return response;
	}

}
