package com.ey.mapper;

import com.ey.dto.request.CreateShowRequest;
import com.ey.dto.response.ShowResponse;
import com.ey.entity.Movie;
import com.ey.entity.MovieShow;
import com.ey.entity.Screen;

public class ShowMapper {
	
	public static MovieShow toEntity(CreateShowRequest request, Movie movie, Screen screen) {
		MovieShow show = new MovieShow();
		show.setMovie(movie);
		show.setScreen(screen);
		show.setShowDate(request.getShowDate());
		show.setStartTime(request.getStartTime());
		return show;
	}
	
	public static ShowResponse toResponse(MovieShow show) {
		ShowResponse response = new ShowResponse();
		response.setShowId(show.getShowId());
		response.setMovieId(show.getMovie().getMovieId());
		response.setScreenId(show.getScreen().getScreenId());
		response.setShowDate(show.getShowDate());
		response.setStartTime(show.getStartTime());
		response.setActive(show.getActive());
		return response;
	}

}
