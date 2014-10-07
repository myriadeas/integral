package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemCategoryRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemCategoryDatabasePopulator")
public class ItemCategoryDatabasePopulator extends AbstractDatabasePopulator implements ItemCategoryDatabasePopulatorIntf {

	@Autowired
	private ItemCategoryRepositoryImpl itemCategoryRepository;

	public void init() {
		List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
		itemCategories.add(OPEN_SHELF);
		itemCategories.add(RED_SPOT);
		itemCategoryRepository.save(itemCategories);
	}


}
