package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShowSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showSeatId;
	
	private Long showId;
	
	private Long seatId;
	
	private String status;

	public Long getShowSeatId() {
		return showSeatId;
	}

	public void setShowSeatId(Long showSeatId) {
		this.showSeatId = showSeatId;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
