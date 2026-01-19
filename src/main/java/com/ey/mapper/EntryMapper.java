package com.ey.mapper;

import java.time.LocalDateTime;

import com.ey.dto.request.CreateEntryRequest;
import com.ey.dto.response.EntryResponse;
import com.ey.entity.Booking;
import com.ey.entity.Entry;

public class EntryMapper {

	public static Entry toEntity(CreateEntryRequest request, Booking booking) {

		Entry entry = new Entry();
		entry.setBooking(booking);
		entry.setEnteredBy(request.getEnteredBy());
		entry.setEnteredAt(LocalDateTime.now());

		return entry;
	}

	public static EntryResponse toResponse(Entry entry) {

		EntryResponse response = new EntryResponse();
		response.setEntryId(entry.getEntryId());
		response.setBookingId(entry.getBooking().getBookingId());
		response.setBookingStatus(entry.getBooking().getBookingStatus());
		response.setEnteredAt(entry.getEnteredAt());
		response.setEnteredBy(entry.getEnteredBy());

		return response;
	}
}
