package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;
import my.com.myriadeas.integral.data.jpa.repositories.PatronCategoryRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PatronCategoryRepositoryImpl extends PatronCategoryRepository {
	public List<PatronCategory> findByCodeContaining(
			@Param("search") String code, Pageable pageable);

	public List<PatronCategory> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);
}