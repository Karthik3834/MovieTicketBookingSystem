package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.ShowSeat;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>{
	
	List<ShowSeat> findByShow_ShowId(Long showId);
	
	List<ShowSeat> findByShow_ShowIdAndStatus(Long showId, String status);
	
	boolean existsByShow_ShowId(Long showId);

}
