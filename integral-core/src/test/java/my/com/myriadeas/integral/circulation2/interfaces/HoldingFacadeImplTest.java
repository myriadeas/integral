package my.com.myriadeas.integral.circulation2.interfaces;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.circulation2.AbstractCirculationIntegrationTest;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HoldingFacadeImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	private HoldingFacade holdingFacade;

	@Autowired
	ItemCategoryRepository itemCategoryRepository;

	@Test
	public void testNewHolding() {
		ItemCategory itemCategory = new ItemCategory("TEST_RS");
		itemCategoryRepository.save(itemCategory);
		NewHoldingRequestDTO request = new NewHoldingRequestDTO("0000000001",
				"TEST_RS");
		NewHoldingResponseDTO response = holdingFacade.newHolding(request);
		assertNotNull(response.getHoldingId());
	}

}
