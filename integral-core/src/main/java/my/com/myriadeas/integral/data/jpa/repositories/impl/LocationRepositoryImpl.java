package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.repositories.LocationRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface LocationRepositoryImpl extends LocationRepository {
	public List<Location> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<Location> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);

}