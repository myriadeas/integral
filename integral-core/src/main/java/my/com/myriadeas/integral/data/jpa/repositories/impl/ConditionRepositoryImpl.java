package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Condition;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ConditionRepositoryImpl extends
		my.com.myriadeas.integral.data.jpa.repositories.ConditionRepository {
	public List<Condition> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<Condition> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);
}