package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.ShowSeat;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>{
	
	List<ShowSeat> findByShowId(Long showId);
	
	List<ShowSeat> findByShowIdAndStatus(Long showId, String status);
	
	boolean existsByShowId(Long showId);

}
