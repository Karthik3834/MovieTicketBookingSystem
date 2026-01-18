package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateTheatreRequest {
	
	@NotBlank(message = "Theatre name cannot be empty")
	private String name;
	
	@NotBlank(message = "Location name cannot be empty")
	private String location;
	
	@NotBlank(message = "Address name cannot be empty")
	private String address;
	
	@NotNull(message = "Number of Screens required")
	@Positive(message = "Number of screens must be greater than 0")
	private Integer noOfScreens;

	public CreateTheatreRequest() {
		super();
	}

	public CreateTheatreRequest(@NotBlank(message = "Theatre name cannot be empty") String name,
			@NotBlank(message = "Location name cannot be empty") String location,
			@NotBlank(message = "Address name cannot be empty") String address,
			@NotNull(message = "Number of Screens required") @Positive(message = "Number of screens must be greater than 0") Integer noOfScreens) {
		super();
		this.name = name;
		this.location = location;
		this.address = address;
		this.noOfScreens = noOfScreens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNoOfScreens() {
		return noOfScreens;
	}

	public void setNoOfScreens(Integer noOfScreens) {
		this.noOfScreens = noOfScreens;
	}

	
	
	
	

}
