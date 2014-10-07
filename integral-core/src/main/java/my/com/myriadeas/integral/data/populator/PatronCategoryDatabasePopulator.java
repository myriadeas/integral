package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;
import my.com.myriadeas.integral.data.jpa.repositories.PatronCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "patronCategoryDatabasePopulator")
public class PatronCategoryDatabasePopulator extends AbstractDatabasePopulator implements PatronCategoryDatabasePopulatorIntf {

	@Autowired
	private PatronCategoryRepository patronCategoryRepository;

	public void init() {
		List<PatronCategory> patronCategories = new ArrayList<PatronCategory>();
		patronCategories.add(STUDENT_CATEGORY);
		patronCategories.add(LECTURER_CATEGORY);
		patronCategories.add(STAFF_CATEGORY);
		patronCategoryRepository.save(patronCategories);
	}


}
