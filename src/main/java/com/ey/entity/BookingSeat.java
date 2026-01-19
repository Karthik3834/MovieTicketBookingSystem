package com.ey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookingSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingSeatId;
	
//	private Long bookingId;
//	
//	private Long showSeatId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id",nullable = false)
	private Booking booking;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "show_seat_id",nullable = false)
	private ShowSeat showSeat;

	public Long getBookingSeatId() {
		return bookingSeatId;
	}

	public void setBookingSeatId(Long bookingSeatId) {
		this.bookingSeatId = bookingSeatId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public ShowSeat getShowSeat() {
		return showSeat;
	}

	public void setShowSeat(ShowSeat showSeat) {
		this.showSeat = showSeat;
	}

	
	
	

}
