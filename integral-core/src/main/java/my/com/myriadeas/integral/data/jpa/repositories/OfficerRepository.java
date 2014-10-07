package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.Officer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface OfficerRepository extends JpaRepository<Officer, Long> {

	@RestResource(exported = false)
	public Officer findByUsername(@Param("search") String username);
}