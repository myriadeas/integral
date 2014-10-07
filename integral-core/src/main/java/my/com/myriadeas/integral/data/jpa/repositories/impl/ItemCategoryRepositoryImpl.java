package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.repositories.ItemCategoryRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ItemCategoryRepositoryImpl extends ItemCategoryRepository {

	public List<ItemCategory> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<ItemCategory> findByDescriptionContaining(@Param("search") String code,
			Pageable pageable);
}