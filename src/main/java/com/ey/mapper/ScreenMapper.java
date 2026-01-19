package com.ey.mapper;

import com.ey.dto.request.CreateScreenRequest;
import com.ey.dto.response.ScreenResponse;
import com.ey.entity.Screen;

public class ScreenMapper {
	
	public static Screen toEntity(CreateScreenRequest request) {
		Screen screen  =new Screen();
		screen.setTheatreId(request.getTheatreId());
		screen.setName(request.getName());
		return screen;
	}
	public static ScreenResponse toResponse(Screen screen) {
		ScreenResponse response = new ScreenResponse();
		response.setScreenId(screen.getScreenId());
		response.setTheatreId(screen.getTheatreId());
		response.setName(screen.getName());
		response.setActive(screen.getActive());
		return response;
	}

}
