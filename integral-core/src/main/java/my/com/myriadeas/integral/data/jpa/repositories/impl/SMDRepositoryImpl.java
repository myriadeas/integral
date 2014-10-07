package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.SMD;
import my.com.myriadeas.integral.data.jpa.repositories.SMDRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface SMDRepositoryImpl extends SMDRepository {

	public List<SMD> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<SMD> findByDescriptionContaining(@Param("search") String description,
			Pageable pageable);
}