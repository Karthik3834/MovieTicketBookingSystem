package com.ey.mapper;

import com.ey.dto.request.CreateScreenRequest;
import com.ey.dto.response.ScreenResponse;
import com.ey.entity.Screen;
import com.ey.entity.Theatre;

public class ScreenMapper {
	
	public static Screen toEntity(CreateScreenRequest request, Theatre theatre) {
		Screen screen  =new Screen();
		screen.setTheatre(theatre);
		screen.setName(request.getName());
		return screen;
	}
	public static ScreenResponse toResponse(Screen screen) {
		ScreenResponse response = new ScreenResponse();
		response.setScreenId(screen.getScreenId());
		response.setTheatreId(screen.getTheatre().getTheatreId());
		response.setName(screen.getName());
		response.setActive(screen.getActive());
		return response;
	}

}
