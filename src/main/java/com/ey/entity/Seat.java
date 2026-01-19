package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	
	private Long screenId;
	
	private String seatTag;
	
	private String rowLabel;
	
	private Integer seatNumber;

	public Seat() {
		super();
	}

	public Seat(Long seatId, Long screenId, String seatTag, String rowLabel, Integer seatNumber) {
		super();
		this.seatId = seatId;
		this.screenId = screenId;
		this.seatTag = seatTag;
		this.rowLabel = rowLabel;
		this.seatNumber = seatNumber;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public String getSeatTag() {
		return seatTag;
	}

	public void setSeatTag(String seatTag) {
		this.seatTag = seatTag;
	}

	public String getRowLabel() {
		return rowLabel;
	}

	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	

}
