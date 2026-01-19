package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateScreenRequest {
	
	
	@NotNull(message = "Theatre ID is required")
	private Long theatreId;
	
	@NotBlank(message = "Screen name cannot be empty")
	private String name;
	
	

	public CreateScreenRequest() {
		super();
	}



	public CreateScreenRequest(@NotNull(message = "Theatre ID is required") Long theatreId,
			@NotBlank(message = "Screen name cannot be empty") String name) {
		super();
		this.theatreId = theatreId;
		this.name = name;
	}



	public Long getTheatreId() {
		return theatreId;
	}



	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	
	
	

}
