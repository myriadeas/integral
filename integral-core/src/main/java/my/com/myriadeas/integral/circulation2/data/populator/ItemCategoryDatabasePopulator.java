package my.com.myriadeas.integral.circulation2.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.infrastructure.jpa.ItemCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.populator.DatabaseInitializingBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemCategoryDatabasePopulator")
public class ItemCategoryDatabasePopulator implements ItemCategoryData,
		DatabaseInitializingBean {

	@Autowired
	private ItemCategoryRepositoryImpl itemCategoryRepository;

	public void init() {
		List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
		itemCategories.add(OPEN_SHELF);
		itemCategories.add(RED_SPOT);
		itemCategoryRepository.save(itemCategories);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}
