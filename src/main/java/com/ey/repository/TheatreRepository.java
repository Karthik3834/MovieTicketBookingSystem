package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>{

}
