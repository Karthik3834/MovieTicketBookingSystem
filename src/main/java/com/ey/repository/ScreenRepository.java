package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>{

}
