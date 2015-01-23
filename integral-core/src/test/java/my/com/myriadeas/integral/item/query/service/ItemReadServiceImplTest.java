package my.com.myriadeas.integral.item.query.service;

import java.util.List;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.item.AbstractItemIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemReadServiceImplTest extends AbstractItemIntegrationTest {

	@Autowired
	private ItemReadService itemReadService;

	@Test
	public void testGetItemListByTitle() {
		List<Item> itemList = itemReadService.getItemListByTitle("Java Programming");
		System.out.println("itemList=" + itemList);
	}

}
