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
	public void testGetItemListByTitleAuthorIsbn() {
		List<Item> itemList = itemReadService.getItemListByTitleAuthorIsbn("title", "author", "isbn");
		System.out.println("itemList=" + itemList);
	}

}
