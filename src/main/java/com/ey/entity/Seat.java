package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	
//	private Long screenId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id",nullable = false)
	private Screen screen;
	
	private String seatTag;
	
	private String rowLabel;
	
	private Integer seatNumber;

	public Seat() {
		super();
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
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
