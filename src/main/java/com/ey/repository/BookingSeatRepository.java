package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.BookingSeat;

@Repository
public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long>{
	
	List<BookingSeat> findByBooking_BookingId(Long bookingId);
	
	boolean existsByShowSeat_ShowSeatId(Long showSeatId);

}
