package my.com.myriadeas.integral.mystic;

import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;

import org.junit.Test;

public class FinesTransactionServiceTest extends
		IntegralAbstractTransactionalJUnit4SpringContextTests {
	
	@Test
	public void testMarker() throws Exception {
	}

	/*
	@Test
	public void testCheckMaxFines() {
		BigDecimal fines = new BigDecimal(5);
		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27maxfine(new BigDecimal(10));
		Glpatrelig glpatrelig = new Glpatrelig();
		glpatrelig.setGl77maxfine(new BigDecimal(30));
		BigDecimal finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(5), finalFines);

		fines = new BigDecimal(20);
		glpatritemelig.setGl27maxfine(new BigDecimal(10));
		glpatrelig.setGl77maxfine(new BigDecimal(30));
		finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(10), finalFines);

		fines = new BigDecimal(20);
		glpatritemelig.setGl27maxfine(new BigDecimal(0));
		glpatrelig.setGl77maxfine(new BigDecimal(10));
		finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(10), finalFines);

		fines = new BigDecimal(20);
		glpatritemelig.setGl27maxfine(new BigDecimal(0));
		glpatrelig.setGl77maxfine(new BigDecimal(0));
		finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(20), finalFines);

		fines = new BigDecimal(20);
		glpatritemelig.setGl27maxfine(new BigDecimal(5));
		glpatrelig.setGl77maxfine(new BigDecimal(0));
		finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(5), finalFines);

		fines = new BigDecimal(0);
		glpatritemelig.setGl27maxfine(new BigDecimal(30));
		glpatrelig.setGl77maxfine(new BigDecimal(10));
		finalFines = finesTransactionService.checkMaxFines(fines,
				glpatritemelig, glpatrelig);
		assertEquals(new BigDecimal(0), finalFines);

	}

	*/
}
