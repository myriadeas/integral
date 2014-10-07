package my.com.myriadeas.integral.fines;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import my.com.myriadeas.integral.data.jpa.domain.Fine;
import my.com.myriadeas.integral.fines.FinesCalculator;

import org.easymock.IMockBuilder;
import org.junit.Before;
import org.junit.Test;

public class FinesCalculatorTest {
	FinesCalculator finesCalculator = new FinesCalculator();

	@Before
	public void setUp() {
		BigDecimal rate = new BigDecimal("0.10");
		Fine fine = new Fine(1, 5, rate);
		finesCalculator.getFines().add(fine);

		rate = new BigDecimal("0.20");
		fine = new Fine(6, 10, rate);
		finesCalculator.getFines().add(fine);

		rate = new BigDecimal("0.50");
		fine = new Fine(11, -1, rate);
		finesCalculator.getFines().add(fine);
	}

	@Test
	public void testGetRate() {
		int position = 0;
		BigDecimal expectedRate;
		BigDecimal resultRate;
		try {
			resultRate = finesCalculator.getRates(position).getRate();
			fail("should throw exception");
		} catch (Exception e) {

		}

		position = 1;
		expectedRate = new BigDecimal("0.10");
		resultRate = finesCalculator.getRates(position).getRate();
		assertEquals(expectedRate, resultRate);

		position = 3;
		expectedRate = new BigDecimal("0.10");
		resultRate = finesCalculator.getRates(position).getRate();
		assertEquals(expectedRate, resultRate);

		position = 5;
		expectedRate = new BigDecimal("0.10");
		resultRate = finesCalculator.getRates(position).getRate();
		assertEquals(expectedRate, resultRate);

		position = 6;
		expectedRate = new BigDecimal("0.20");
		resultRate = finesCalculator.getRates(position).getRate();
		assertEquals(expectedRate, resultRate);

		position = 12;
		expectedRate = new BigDecimal("0.50");
		resultRate = finesCalculator.getRates(position).getRate();
		assertEquals(expectedRate, resultRate);
	}

	@Test
	public void testGetAmount() {
		int lateBy = 8;
		BigDecimal expectedAmount = new BigDecimal("1.10");
		BigDecimal resultAmount = finesCalculator.getAmount(lateBy);
		assertEquals(expectedAmount, resultAmount);

		lateBy = 5;
		expectedAmount = new BigDecimal("0.50");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));

		lateBy = 1;
		expectedAmount = new BigDecimal("0.10");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));

		lateBy = 6;
		expectedAmount = new BigDecimal("0.70");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));

		lateBy = 11;
		expectedAmount = new BigDecimal("2.00");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));

		lateBy = 12;
		expectedAmount = new BigDecimal("2.50");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));

		lateBy = 0;
		expectedAmount = new BigDecimal("0.00");
		resultAmount = finesCalculator.getAmount(lateBy);
		assertTrue(resultAmount.equals(expectedAmount));
	}
	
	@Test
	public void testGetFinesBasedOnMaxFines(){
		BigDecimal actualFines = new BigDecimal(0);
		BigDecimal maxFines = new BigDecimal(0);
		BigDecimal returnFines = finesCalculator.getFinesBasedOnMaxFines(actualFines, maxFines);
		assertTrue(returnFines == actualFines);	
				
		actualFines = new BigDecimal(1);
		maxFines = new BigDecimal(1);
		returnFines = finesCalculator.getFinesBasedOnMaxFines(actualFines, maxFines);
		assertTrue(returnFines == actualFines);	
		
		actualFines = new BigDecimal(2);
		maxFines = new BigDecimal(1);
		returnFines = finesCalculator.getFinesBasedOnMaxFines(actualFines, maxFines);
		assertTrue(returnFines == maxFines);	
		
		actualFines = new BigDecimal(1);
		maxFines = new BigDecimal(2);
		returnFines = finesCalculator.getFinesBasedOnMaxFines(actualFines, maxFines);
		assertTrue(returnFines == actualFines);	
		
	}
	
	private void testActualFinesLesserThanPatronItemEligibilityMaxFinesLesserThanPatronEligibilityMaxFines(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(5);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(10);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(30);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronItemEligibilityMaxFines)).andReturn(actualFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines ==  actualFines);
	}
	
	private void testActualFinesMoreThanPatronItemEligibilityMaxFinesLesserThanPatronEligibilityMaxFines(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(20);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(10);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(30);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronItemEligibilityMaxFines)).andReturn(patronItemEligibilityMaxFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines == patronItemEligibilityMaxFines);
		
	}
	
	private void testPatronItemEligibilityMaxFinesIsZeroAndActualFinesMoreThanPatronEligibilityMaxFines(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(20);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(0);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(10);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(false).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronEligibilityMaxFines)).andReturn(patronEligibilityMaxFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines == patronEligibilityMaxFines);
		
	}
	
	private void testPatronItemEligibilityMaxFinesIsZeroAndPatronEligibilityMaxFinesIsZero(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(20);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(0);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(0);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(false).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(false).times(1);
		//expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronEligibilityMaxFines)).andReturn(patronEligibilityMaxFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines == actualFines);
		
	}
	
	private void testActualFInesMoreThanPatronItemEligibilityMaxFinesAndPatronEligibilityMaxFinesIsZero(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(20);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(5);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(0);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(false).times(1);
		expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronItemEligibilityMaxFines)).andReturn(patronItemEligibilityMaxFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines == patronItemEligibilityMaxFines);
		
	}
	
	private void testActualFinesIsZero(){
		org.easymock.EasyMock.reset(finesCalculator);
		BigDecimal actualFines = new BigDecimal(0);
		BigDecimal patronItemEligibilityMaxFines = new BigDecimal(30);
		BigDecimal patronEligibilityMaxFines = new BigDecimal(10);
		expect(finesCalculator.getPatronItemEligibilityMaxFines()).andReturn(patronItemEligibilityMaxFines).times(1);
		expect(finesCalculator.getPatronEligibilityMaxFines()).andReturn(patronEligibilityMaxFines).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronItemEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.isMaxFinesDefined(patronEligibilityMaxFines)).andReturn(true).times(1);
		expect(finesCalculator.getFinesBasedOnMaxFines(actualFines, patronItemEligibilityMaxFines)).andReturn(actualFines).times(1);
		replay(finesCalculator);	
		
		BigDecimal resultFines = finesCalculator.getFinesBasedOnEligibilityMaxFines(actualFines);	
		assertTrue(resultFines == actualFines);
		
	}
		
		
	@Test
	public void testGetFinesBasedOnEligibilityMaxFines(){
		IMockBuilder<FinesCalculator> finesCalculatorMock = createMockBuilder(FinesCalculator.class);
		finesCalculatorMock.addMockedMethod("getPatronItemEligibilityMaxFines");
		finesCalculatorMock.addMockedMethod("getPatronEligibilityMaxFines");
		finesCalculatorMock.addMockedMethod("isMaxFinesDefined");
		finesCalculatorMock.addMockedMethod("getFinesBasedOnMaxFines");		
		this.finesCalculator = finesCalculatorMock.createMock();
		
				
		testActualFinesLesserThanPatronItemEligibilityMaxFinesLesserThanPatronEligibilityMaxFines();		
		testActualFinesMoreThanPatronItemEligibilityMaxFinesLesserThanPatronEligibilityMaxFines();
		testPatronItemEligibilityMaxFinesIsZeroAndActualFinesMoreThanPatronEligibilityMaxFines();		
		testPatronItemEligibilityMaxFinesIsZeroAndPatronEligibilityMaxFinesIsZero();
		testActualFInesMoreThanPatronItemEligibilityMaxFinesAndPatronEligibilityMaxFinesIsZero();
		testActualFinesIsZero();
	}
}
