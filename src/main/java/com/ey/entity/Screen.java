package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long screenId;
	
	private Long theatreId;
	
	private String name;
	
	
	private Boolean active;

	public Screen() {
		super();
	}

	public Screen(Long screenId, Long theatreId, String name, Boolean active) {
		super();
		this.screenId = screenId;
		this.theatreId = theatreId;
		this.name = name;
		this.active = active;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	
	

}
