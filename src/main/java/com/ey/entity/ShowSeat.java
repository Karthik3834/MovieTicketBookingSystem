package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ShowSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showSeatId;
	
//	private Long showId;
//	
//	private Long seatId;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "show_id",nullable = false)
	private MovieShow show;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "seat_id",nullable = false)
	private Seat seat;
	
	private String status;

	public Long getShowSeatId() {
		return showSeatId;
	}

	public void setShowSeatId(Long showSeatId) {
		this.showSeatId = showSeatId;
	}

	public MovieShow getShow() {
		return show;
	}

	public void setShow(MovieShow show) {
		this.show = show;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
