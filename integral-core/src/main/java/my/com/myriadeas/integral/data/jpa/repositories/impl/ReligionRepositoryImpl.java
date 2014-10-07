package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Religion;
import my.com.myriadeas.integral.data.jpa.repositories.ReligionRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ReligionRepositoryImpl extends ReligionRepository{
	
    public List<Religion> findByCodeContaining(@Param("search") String code, Pageable pageable);
	
	public List<Religion> findByDescriptionContaining(@Param("search") String description, Pageable pageable);
	
}