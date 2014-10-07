package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Glbibs;

import org.springframework.data.repository.query.Param;

public interface GlbibsRepository extends
		my.com.myriadeas.integral.data.jpa.repositories.GlbibsRepository {
	public Glbibs findById(@Param("search") Long id);

	public Glbibs findByGl48source(@Param("search") String gl48source);
}