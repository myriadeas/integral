package my.com.myriadeas.integral.mystic;

import my.com.myriadeas.integral.circulation.service.impl.DueDateCalculationServiceImpl;
import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DueDateCalculationServiceTest extends
IntegralAbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DueDateCalculationServiceImpl dueDateCalculationService;
	
	@Autowired
	private PatronItemEligibilityRepositoryImpl glpatritemeligRepo;
	
	@Test
	public void testMarker() throws Exception {
	}

	/*		
	@Test
	public void testCalculateDueDateTimeForDailyLoan(){
		Glbrnc branch = new Glbrnc();
		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime checkOutDateTime = dateFormatter.parseDateTime("01/01/2001 01:01:02");
		
		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27elig(10);
		glpatritemelig.setGl27ploan(10);
		glpatritemelig.setGl27ptype("D");
		glpatritemelig.setGl27patritemelig("patritemelig");
		glpatritemelig.setGl27maxfine(new BigDecimal(50));
		glpatritemeligRepo.save(glpatritemelig);
		
		DateTime expectedDueDateTime = dateFormatter.parseDateTime("11/01/2001 01:01:02");
		DateTime dueDateTime = dueDateCalculationService.calculateDueDateTime(checkOutDateTime, glpatritemelig, branch);
		assertEquals(expectedDueDateTime, dueDateTime);
	}
	
	@Test
	public void testCalculateDueDateTimeForHourlyLoan(){
		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime checkOutDateTime = dateFormatter.parseDateTime("01/01/2001 01:01:02");
		
		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27elig(10);
		glpatritemelig.setGl27ploan(2);
		glpatritemelig.setGl27ptype("H");
		glpatritemelig.setGl27patritemelig("patritemelig");
		glpatritemelig.setGl27maxfine(new BigDecimal(50));
		glpatritemeligRepo.save(glpatritemelig);
		
		DateTime expectedDueDateTime = dateFormatter.parseDateTime("01/01/2001 03:01:02");
		DateTime dueDateTime = dueDateCalculationService.calculateDueDateTimeForHourlyLoan(checkOutDateTime, glpatritemelig);
		assertEquals(expectedDueDateTime, dueDateTime);
	}
	
	*/
}
