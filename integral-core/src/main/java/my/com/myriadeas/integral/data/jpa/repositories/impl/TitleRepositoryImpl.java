package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Title;
import my.com.myriadeas.integral.data.jpa.repositories.TitleRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface TitleRepositoryImpl extends TitleRepository{
	
	public List<Title> findByCodeContaining(@Param("search") String code, Pageable pageable);
	
    public List<Title> findByDescriptionContaining(@Param("search") String description, Pageable pageable);
	
}