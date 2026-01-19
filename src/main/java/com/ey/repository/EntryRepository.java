package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>{
	
	boolean existsByBooking_BookingId(Long bookingId);
	
	Optional<Entry> findByBooking_BookingId(Long BookingId);

}
