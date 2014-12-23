package my.com.myriadeas.integral.circulation2.interfaces;

import static org.junit.Assert.assertNotNull;
import my.com.myriadeas.integral.circulation2.AbstractCirculationIntegrationTest;
import my.com.myriadeas.integral.circulation2.domain.model.Borrower;
import my.com.myriadeas.integral.circulation2.domain.model.BorrowerRepository;
import my.com.myriadeas.integral.circulation2.domain.model.Holding;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategoryRepository;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategory;
import my.com.myriadeas.integral.circulation2.domain.model.PatronCategoryRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BorrowerFacadeImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	private BorrowerFacade borrowerFacade;

	@Autowired
	PatronCategoryRepository patronCategoryRepository;
	
	@Autowired
	private BorrowerRepository borrowerRepository;

	public void testRegister() {
		PatronCategory patronCategory = new PatronCategory("TEST_STU", "Test Student");
		Borrower borrower = new Borrower("borrower_02", new Long("0000000001"), null);
		borrowerRepository.save(borrower);
		patronCategoryRepository.save(patronCategory);
		RegisterBorrowerRequestDTO registerBorrowerRequestDTO = new RegisterBorrowerRequestDTO("borrower_02", "TEST_STU");
		RegisterBorrowerResponseDTO registerBorrowerResponseDTO = borrowerFacade.register(registerBorrowerRequestDTO);
		assertNotNull(registerBorrowerResponseDTO.getId());
	}

}
