package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{
	
	List<Seat> findByScreenId(Long screenId);
	
	long countByScreenId(Long screenId);
	
	boolean existsByScreenId(Long screenId);

}
