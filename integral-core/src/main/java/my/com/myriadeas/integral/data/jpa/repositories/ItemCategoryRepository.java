package my.com.myriadeas.integral.data.jpa.repositories;

import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface ItemCategoryRepository extends
		JpaRepository<ItemCategory, Long> {

	@RestResource(exported = false)
	public ItemCategory findByCode(String code);
}