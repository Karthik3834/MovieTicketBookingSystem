package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long>{
	
	
	boolean existsByBooking_BookingId(Long bookingId);
	
	Optional<Refund> findByBooking_BookingId(Long bookingId);

}
