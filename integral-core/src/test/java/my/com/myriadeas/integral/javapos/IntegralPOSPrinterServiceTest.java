package my.com.myriadeas.integral.javapos;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntegralPOSPrinterServiceTest {
	IntegralPOSPrinterService service;

	String PrinterDevice = "posPrinter";

	@Before
	public void before() {
		service = new IntegralPOSPrinterService();
	}

	@Test
	public void testPOSPrinter() throws Exception {

		my.com.myriadeas.integral.javapos.POSPrinter posPrinter = new my.com.myriadeas.integral.javapos.POSPrinter();
		posPrinter.setCutPaperPercentage(80);
		posPrinter.setStation(1);
		posPrinter.setData("Welcome to Integral receipt printing test");
		posPrinter.printReceiptAndCutPaper();
	}

	@Test
	public void testPOSPrinterBean() throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"posPrinter.xml");
		my.com.myriadeas.integral.javapos.POSPrinter posPrinter = (my.com.myriadeas.integral.javapos.POSPrinter) context
				.getBean("posprinterbean");
		posPrinter.setData("Welcome to Integral receipt printing test");
		posPrinter.printReceiptAndCutPaper();
	}

	@Test
	public void testPOSPrinterFactoryBean() throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"posPrinter.xml");

		IntegralPOSPrinterService posPrinterService = (IntegralPOSPrinterService) context
				.getBean("posprinterfactorybean");

		/*--
		SimulatedPOSPrinterService posPrinterService = (SimulatedPOSPrinterService) context
			.getBean("posprinterfactorybean");
		 */
		posPrinterService.printNormal(1, "Hello World");
		posPrinterService.cutPaper(70);
	}

}
