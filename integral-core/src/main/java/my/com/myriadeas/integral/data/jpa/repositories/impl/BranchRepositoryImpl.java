package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.repositories.BranchRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface BranchRepositoryImpl extends BranchRepository{
	
    public List<Branch> findByCodeContaining(@Param("search") String code, Pageable pageable);
	
    public List<Branch> findByDescriptionContaining(@Param("search") String description, Pageable pageable);
	
}