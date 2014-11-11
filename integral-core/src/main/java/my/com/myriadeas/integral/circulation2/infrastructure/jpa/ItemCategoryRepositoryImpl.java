package my.com.myriadeas.integral.circulation2.infrastructure.jpa;

import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepositoryImpl extends
		JpaRepository<ItemCategory, Long>, ItemCategoryRepository {

}
