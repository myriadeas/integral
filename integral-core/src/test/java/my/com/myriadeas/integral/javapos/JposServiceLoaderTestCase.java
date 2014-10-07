package my.com.myriadeas.integral.javapos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import jpos.JposException;
import jpos.config.JposEntry;
import jpos.config.JposEntryRegistry;
import jpos.loader.JposServiceConnection;
import jpos.loader.JposServiceLoader;
import jpos.loader.JposServiceManager;

/**
 * A JUnit TestCase for the class JposServiceLoader
 * 
 * @author E. Michael Maximilien (maxim@us.ibm.com)
 */
public class JposServiceLoaderTestCase extends AbstractTestCase {
	// -------------------------------------------------------------------------
	// Ctor(s)
	//

	public JposServiceLoaderTestCase(String name) {
		super(name);
	}

	// -------------------------------------------------------------------------
	// Protected overridden methods
	//

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected boolean useSimpleProfile() {
		return true;
	}

	// -------------------------------------------------------------------------
	// Public testXyz() methods
	//

	public void testFindService() throws JposException {
		JposServiceLoader.getManager().getProperties().loadJposProperties();
		JposEntryRegistry registry = JposServiceLoader.getManager()
				.getEntryRegistry();

		Enumeration entriesEnum = registry.getEntries();

		while (entriesEnum.hasMoreElements()) {
			JposEntry entry = (JposEntry) entriesEnum.nextElement();

			// way to get jpos category
			String jposCategory = (String) entry
					.getPropertyValue("deviceCategory");
			System.out.println(jposCategory);
			if (jposCategory.equals("POSPrinter")
					|| jposCategory.equals("POS Printer")) {

				JposServiceConnection connection = JposServiceLoader
						.findService(entry.getLogicalName());

				System.out.println(connection.getClass());
				connection.connect();
				System.out.println(connection.getService().getClass());

				Class<?> posPrinterService;
				try {
					posPrinterService = Class.forName(connection.getService()
							.getClass().getName());
					Object simulatedPOSPrinterService = posPrinterService
							.newInstance();
					Method m = posPrinterService.getDeclaredMethod("cutPaper",
							int.class);
					m.invoke(simulatedPOSPrinterService, 70);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Enumeration props = entry.getPropertyNames();
				assertTrue(
						"Could not get connection for existing entry with logical name = "
								+ entry.getLogicalName(), connection != null);

				assertTrue(
						"JposServiceConnection.getLogicalName() == "
								+ connection.getLogicalName()
								+ " is different than the JposEntry.getLogicalName() == "
								+ entry.getLogicalName(), connection
								.getLogicalName()
								.equals(entry.getLogicalName()));
				System.out.println(entry.getLogicalName());
			}
		}
	}

	public void testGetManager() {
		assertTrue("JposServiceLoader.getManager() returned null",
				JposServiceLoader.getManager() != null);

		assertTrue(
				"JposServiceLoader.getManager() must be an instance of JposServiceManager",
				JposServiceLoader.getManager() instanceof JposServiceManager);
	}
}
