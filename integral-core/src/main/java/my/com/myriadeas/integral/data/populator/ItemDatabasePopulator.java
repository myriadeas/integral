package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.repositories.ConditionRepository;
import my.com.myriadeas.integral.data.jpa.repositories.LocationRepository;
import my.com.myriadeas.integral.data.jpa.repositories.MaterialRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.SMDRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemDatabasePopulator")
public class ItemDatabasePopulator extends AbstractDatabasePopulator implements ItemDatabasePopulatorIntf {

	@Autowired
	ItemRepositoryImpl itemRepository;

	@Autowired
	ItemCategoryRepositoryImpl itemCategoryRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MaterialRepository materialRepository;

	@Autowired
	SMDRepositoryImpl smdRepository;

	@Autowired
	ConditionRepository conditionRepository;

	@Autowired
	ItemCategoryDatabasePopulatorIntf itemCategoryDatabasePopulator;

	@Autowired
	SmdDatabasePopulatorIntf smdDatabasePopulator;

	@Autowired
	LocationDatabasePopulatorIntf locationDatabasePopulator;

	@Autowired
	MaterialDatabasePopulatorIntf materialDatabasePopulator;

	@Autowired
	ConditionDatabasePopulatorIntf conditionDatabasePopulator;

	public void init() {
		List<Item> items = new ArrayList<Item>();
		items.add(DOC1);
		items.add(DOC2);
		items.add(DOC3);
		items.add(DOC4);
		items.add(DOC5);
		items.add(CIRCULATED_ITEM);
		items.add(CIRCULATED_ITEM_1);
		items.add(CIRCULATED_ITEM_2);
		items.add(CIRCULATED_ITEM_3);
		items.add(CIRCULATED_ITEM_4);

		items.add(MISSING_ITEM);
		items.add(ONRECALL_ITEM);
		items.add(WEEDED_ITEM);
		items.add(ONHOLD_ITEM_1);
		items.add(MISSING_ITEM);
		items.add(ONHOLD_ITEM_2);
		items.add(AVAILABLE_RED_SPOT_BOOK);
		items.add(AVAILABLE_OPEN_SHELF_BOOK);
		items.add(AVAILABLE_RED_SPOT_VIDEO);
		items.add(AVAILABLE_OPEN_SHELF_VIDEO);
		

		items.add(CIRCULATED_ITEM_RED_SPOT_BOOK_1);
		items.add(CIRCULATED_ITEM_RED_SPOT_BOOK_2);
		items.add(CIRCULATED_ITEM_RED_SPOT_BOOK_3);
		items.add(CIRCULATED_ITEM_RED_SPOT_BOOK_4);
		items.add(CIRCULATED_ITEM_RED_SPOT_BOOK_5);
		items.add(CIRCULATED_ITEM_OPEN_SHELF_BOOK);
		
		items.add(AVAILABLE_OPEN_SHELF_BOOK_1);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_2);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_3);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_4);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_5);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_6);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_7);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_8);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_9);
		items.add(AVAILABLE_OPEN_SHELF_BOOK_10);
		
		items.add(CIRCULATED_ITEM_OPEN_SHELF_BOOK_SIEWMEEYEE);
		

		items.add(CIRCULATED_OVERDUE_ITEM_01);
		items.add(CIRCULATED_OVERDUE_ITEM_02);
		
		itemRepository.save(items);
	}

}
