package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.circulation2.AbstractCirculationIntegrationTest;
import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HoldingServiceImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	HoldingService holdingService;

	@Autowired
	ItemCategoryRepository itemCategoryRepository;

	@Autowired
	HoldingRepository holdingRepository;
	
	@Test
	public void testNewHolding() {
		NewHoldingCommand command = new NewHoldingCommand("0000000001");
		holdingService.newHolding(command);
	}

	@Test
	public void testRelease() {
		ItemCategory itemCategory = new ItemCategory("TEST_RS");
		Holding holding = new Holding("0000000001");
		holdingRepository.save(holding);
		itemCategoryRepository.save(itemCategory);
		ReleaseHoldingCommand command = new ReleaseHoldingCommand("0000000001",
				"TEST_RS");
		holdingService.release(command);
	}

}
