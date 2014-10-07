package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.UserGroup;
import my.com.myriadeas.integral.data.jpa.repositories.UserGroupRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface UserGroupRepositoryImpl extends UserGroupRepository {

	public Page<UserGroup> findByCodeIgnoreCaseContaining(
			@Param("search") String code, Pageable pageable);

	public Page<UserGroup> findByDescriptionIgnoreCaseContaining(
			@Param("search") String description, Pageable pageable);
}