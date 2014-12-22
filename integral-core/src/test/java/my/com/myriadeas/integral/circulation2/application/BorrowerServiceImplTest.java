package my.com.myriadeas.integral.circulation2.application;

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

public class BorrowerServiceImplTest extends AbstractCirculationIntegrationTest {

	@Autowired
	BorrowerService borrowerService;

	@Autowired
	PatronCategoryRepository patronCategoryRepository;

	@Autowired
	BorrowerRepository borrowerRepository;

	@Test
	public void testNewBorrower() {
		NewBorrowerCommand newBorrowerCommand = new NewBorrowerCommand(
				"borrower_01", new Long("0000000001"));
		borrowerService.newBorrower(newBorrowerCommand);
	}

	@Test
	public void testRegister() {
		PatronCategory patronCategory = new PatronCategory("TEST_LEC", "Test Lecturer");
		Borrower borrower = new Borrower("borrower_01", new Long("0000000001"), null);
		borrowerRepository.save(borrower);
		patronCategoryRepository.save(patronCategory);
		RegisterBorrowerCommand registerBorrowerCommand = new RegisterBorrowerCommand(borrower.getUsername(),
				patronCategory.getCode());
		borrowerService.registerBorrower(registerBorrowerCommand);
	}

}
