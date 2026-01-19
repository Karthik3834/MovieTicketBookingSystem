package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>{
	
	List<Screen> findByTheatre_TheatreId(Long theatreId);

}
