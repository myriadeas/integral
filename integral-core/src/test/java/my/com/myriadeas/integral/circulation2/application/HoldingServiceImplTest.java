package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HoldingServiceImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	HoldingService holdingService;
	
	@Autowired
	ItemCategoryRepository itemCategoryRepository;

	@Test
	public void testNewHolding() {
		ItemCategory itemCategory = new ItemCategory("RS");
		itemCategoryRepository.save(itemCategory);
		NewHoldingCommand command = new NewHoldingCommand("0000000001", "RS");
		holdingService.newHolding(command);
	}

}
