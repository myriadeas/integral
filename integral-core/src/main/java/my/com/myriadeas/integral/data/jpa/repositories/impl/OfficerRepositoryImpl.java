package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.OfficerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfficerRepositoryImpl extends OfficerRepository {

	public Page<Officer> findByUsernameContaining(@Param("search") String username, Pageable pageable);

	@Query("SELECT b FROM Officer b WHERE b.firstname LIKE '%' || :search || '%' or b.lastname LIKE '%' || :search || '%'")
	public Page<Officer> findByNameContaining(@Param("search") String name,
			Pageable pageable);

	public Page<Officer> findByNewICContaining(@Param("search") String newIC,
			Pageable pageable);
}