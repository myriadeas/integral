package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface PatronItemEligibilityRepository extends
		JpaRepository<PatronItemEligibility, Long> {
	

	@RestResource(exported = false)
	public PatronItemEligibility findByCode(
			@Param("code") String code);
	
}