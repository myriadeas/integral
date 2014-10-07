package my.com.myriadeas.integral.javapos;

import java.util.Enumeration;

import jpos.config.JposEntry;
import jpos.config.JposEntryRegistry;
import jpos.loader.JposServiceConnection;
import jpos.loader.JposServiceLoader;

import org.springframework.beans.factory.FactoryBean;

public class POSPrinterFactoryBean implements FactoryBean {

	private String logicalName;

	public String getLogicalName() {
		return logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	@Override
	public Object getObject() throws Exception {
		JposServiceLoader.getManager().getProperties().loadJposProperties();
		JposEntryRegistry registry = JposServiceLoader.getManager()
				.getEntryRegistry();

		Enumeration entriesEnum = registry.getEntries();

		String posPrinterLogicalName = "";
		String firstPOSPrinterLogicalName = "";
		while (entriesEnum.hasMoreElements()) {
			JposEntry entry = (JposEntry) entriesEnum.nextElement();

			String jposCategory = (String) entry
					.getPropertyValue("deviceCategory");
			if (jposCategory.equals("POSPrinter")
					|| jposCategory.equals("POS Printer")) {
				if (firstPOSPrinterLogicalName.length() == 0) {
					firstPOSPrinterLogicalName = entry.getLogicalName();
				}
			}

			if (entry.LOGICAL_NAME_PROP_NAME.equals(this.logicalName)) {
				posPrinterLogicalName = this.logicalName;
				break;
			}

		}

		if (posPrinterLogicalName.length() <= 0) {
			posPrinterLogicalName = firstPOSPrinterLogicalName;
		}

		JposServiceConnection connection = JposServiceLoader
				.findService(logicalName);
		connection.connect();

		Class<?> posPrinterService;

		posPrinterService = Class.forName(connection.getService().getClass()
				.getName());

		return posPrinterService.newInstance();

	}

	@Override
	public Class getObjectType() {
		Class<?> objectClass = null;
		try {
			objectClass = this.getObject().getClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objectClass;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
