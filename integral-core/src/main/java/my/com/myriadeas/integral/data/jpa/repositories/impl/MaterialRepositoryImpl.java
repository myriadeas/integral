package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Material;

import org.springframework.data.repository.query.Param;

public interface MaterialRepositoryImpl extends
		my.com.myriadeas.integral.data.jpa.repositories.MaterialRepository {
	
	public Material findById(@Param("search") Long id);
	public Material findByMaterialNo(@Param("search") String materialNo);
}