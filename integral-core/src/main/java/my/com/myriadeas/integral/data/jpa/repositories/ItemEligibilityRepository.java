package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.ItemEligibility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface ItemEligibilityRepository extends
		JpaRepository<ItemEligibility, Long> {

	@RestResource(exported = false)
	public ItemEligibility findByCode(
			@Param("code") String code);
}