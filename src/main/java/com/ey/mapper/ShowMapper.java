package com.ey.mapper;

import com.ey.dto.request.CreateShowRequest;
import com.ey.dto.response.ShowResponse;
import com.ey.entity.MovieShow;

public class ShowMapper {
	
	public static MovieShow toEntity(CreateShowRequest request) {
		MovieShow show = new MovieShow();
		show.setScreenId(request.getScreenId());
		show.setMovieId(request.getMovieId());
		show.setShowDate(request.getShowDate());
		show.setStartTime(request.getStartTime());
		return show;
	}
	
	public static ShowResponse toResponse(MovieShow show) {
		ShowResponse response = new ShowResponse();
		response.setShowId(show.getShowId());
		response.setScreenId(show.getScreenId());
		response.setMovieId(show.getMovieId());
		response.setShowDate(show.getShowDate());
		response.setStartTime(show.getStartTime());
		response.setActive(show.getActive());
		return response;
	}

}
