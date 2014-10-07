package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Town;
import my.com.myriadeas.integral.data.jpa.repositories.TownRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface TownRepositoryImpl extends TownRepository {
	public List<Town> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<Town> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);
}