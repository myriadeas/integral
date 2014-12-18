package my.com.myriadeas.integral.circulation2.interfaces;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.circulation2.AbstractCirculationIntegrationTest;
import my.com.myriadeas.integral.circulation2.domain.model.BorrowerRepository;
import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategoryRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HoldingFacadeImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	private HoldingFacade holdingFacade;

	@Autowired
	ItemCategoryRepository itemCategoryRepository;
	
	@Autowired
	private HoldingRepository holdingRepository;

	@Test
	public void testRegister() {
		ItemCategory itemCategory = new ItemCategory("TEST_RS");
		Holding holding = new Holding("0000000001");
		holdingRepository.save(holding);
		itemCategoryRepository.save(itemCategory);
		ReleaseHoldingRequestDTO request = new ReleaseHoldingRequestDTO(
				"0000000001", "TEST_RS");
		ReleaseHoldingResponseDTO response = holdingFacade.release(request);
		assertNotNull(response.getHoldingId());
	}

}
