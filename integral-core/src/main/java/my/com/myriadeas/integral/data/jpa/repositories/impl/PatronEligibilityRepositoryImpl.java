package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.PatronEligibilityRepository;

import org.springframework.data.repository.query.Param;

public interface PatronEligibilityRepositoryImpl extends
		PatronEligibilityRepository {

	public PatronEligibility findByCode(@Param("code") String code);

}