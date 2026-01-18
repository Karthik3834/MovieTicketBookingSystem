package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long theatreId;
	
	private String name;
	private String location;
	private String address;
	private Integer noOfScreens;
	private Boolean active;
	public Theatre() {
		super();
	}
	public Theatre(Long theatreId, String name, String location, String address, Integer noOfScreens, Boolean active) {
		super();
		this.theatreId = theatreId;
		this.name = name;
		this.location = location;
		this.address = address;
		this.noOfScreens = noOfScreens;
		this.active = active;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	

}
