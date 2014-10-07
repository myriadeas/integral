package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Race;
import my.com.myriadeas.integral.data.jpa.repositories.RaceRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface RaceRepositoryImpl extends RaceRepository{
	
	public List<Race> findByCodeContaining(@Param("search") String code, Pageable pageable);
	
	public List<Race> findByDescriptionContaining(@Param("search") String description, Pageable pageable);
	
}