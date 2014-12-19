package my.com.myriadeas.integral.circulation2.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.circulation2.domain.model.PatronCategory;
import my.com.myriadeas.integral.circulation2.infrastructure.jpa.PatronCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.populator.DatabaseInitializingBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "patronCategoryDatabasePopulator")
public class PatronCategoryDatabasePopulator implements PatronCategoryData,
		DatabaseInitializingBean {

	@Autowired
	private PatronCategoryRepositoryImpl patronCategoryRepository;

	public void init() {
		List<PatronCategory> patronCategories = new ArrayList<PatronCategory>();
		patronCategories.add(LECTURER);
		patronCategories.add(STUDENT);
		patronCategoryRepository.save(patronCategories);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}
