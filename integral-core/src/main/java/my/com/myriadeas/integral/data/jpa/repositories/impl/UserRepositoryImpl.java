package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Officer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryImpl extends JpaRepository<Officer, Long>  {
	public Officer findByUsername(@Param("search") String username);
}