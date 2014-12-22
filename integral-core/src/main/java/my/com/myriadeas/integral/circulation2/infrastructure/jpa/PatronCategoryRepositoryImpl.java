package my.com.myriadeas.integral.circulation2.infrastructure.jpa;

import my.com.myriadeas.integral.circulation2.domain.model.PatronCategory;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronCategoryRepositoryImpl extends
		JpaRepository<PatronCategory, Long>, PatronCategoryRepository {

}
