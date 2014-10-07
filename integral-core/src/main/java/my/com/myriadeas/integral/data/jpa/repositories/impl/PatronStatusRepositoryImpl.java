package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatronStatusRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.PatronStatusRepository{
	public PatronStatus findById(@Param("search") Long id);
	public PatronStatus findByCode(@Param("search") String code);
	
	@Query("SELECT b FROM PatronStatus b WHERE UPPER(b.code) LIKE '%' || UPPER(:search) || '%'")
    public List<PatronStatus> findByCodeContaining(@Param("search") String code, Pageable pageable);
	
	@Query("SELECT b FROM PatronStatus b WHERE UPPER(b.description) LIKE '%' || UPPER(:search) || '%'")
    public List<PatronStatus> findByDescriptionContaining(@Param("search") String description, Pageable pageable);
	
}