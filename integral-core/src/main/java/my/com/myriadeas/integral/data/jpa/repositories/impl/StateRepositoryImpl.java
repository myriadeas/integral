package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.State;
import my.com.myriadeas.integral.data.jpa.repositories.StateRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface StateRepositoryImpl extends StateRepository {
	public List<State> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<State> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);
}