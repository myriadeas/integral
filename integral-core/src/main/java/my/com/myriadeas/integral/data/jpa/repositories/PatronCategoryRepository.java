package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface PatronCategoryRepository extends
		JpaRepository<PatronCategory, Long> {
	
	@RestResource(exported = false)
	public PatronCategory findByCode(@Param("search") String code);
}