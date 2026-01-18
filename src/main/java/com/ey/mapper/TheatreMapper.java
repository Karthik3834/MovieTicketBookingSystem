package com.ey.mapper;

import com.ey.dto.request.CreateTheatreRequest;
import com.ey.dto.response.TheatreResponse;
import com.ey.entity.Theatre;

public class TheatreMapper {
	public static Theatre toEntity(CreateTheatreRequest request) {
			Theatre theatre = new Theatre();
			theatre.setName(request.getName());
			theatre.setLocation(request.getLocation());
			theatre.setAddress(request.getAddress());
			theatre.setNoOfScreens(request.getNoOfScreens());
			return theatre;
	}
	
	public static TheatreResponse toResponse(Theatre theatre) {
		TheatreResponse response = new TheatreResponse();
		response.setTheatreId(theatre.getTheatreId());
		response.setName(theatre.getName());
		response.setLocation(theatre.getLocation());
		response.setAddress(theatre.getAddress());
		response.setNoOfScreens(theatre.getNoOfScreens());
		response.setActive(theatre.getActive());
		return response;
	}

}
