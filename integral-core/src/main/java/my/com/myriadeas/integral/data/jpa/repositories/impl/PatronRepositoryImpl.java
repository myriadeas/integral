package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PatronRepositoryImpl extends PatronRepository {


	public Page<Patron> findByUsernameContaining(
			@Param("search") String username, Pageable pageable);

	public Page<Patron> findByFirstnameAndLastnameAllIgnoreCaseContaining(
			@Param("search") String firstname, Pageable pageable);

	public Page<Patron> findByNewICContaining(@Param("search") String newIC,
			Pageable pageable);

}