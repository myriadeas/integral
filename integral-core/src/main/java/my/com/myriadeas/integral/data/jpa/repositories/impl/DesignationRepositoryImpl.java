package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Designation;
import my.com.myriadeas.integral.data.jpa.repositories.DesignationRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface DesignationRepositoryImpl extends DesignationRepository {
	public List<Designation> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<Designation> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);

}