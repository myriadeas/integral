package my.com.myriadeas.integral.javapos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import jpos.JposException;
import jpos.config.JposEntry;
import jpos.config.JposEntryRegistry;
import jpos.loader.JposServiceConnection;
import jpos.loader.JposServiceLoader;

public class POSPrinter {

	private String logicalName;

	private int cutPaperPercentage;
	
	private String data;
	
	private int station;

	public String getLogicalName() {
		return logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public int getCutPaperPercentage() {
		return cutPaperPercentage;
	}

	public void setCutPaperPercentage(int cutPaperPercentage) {
		this.cutPaperPercentage = cutPaperPercentage;
	}
	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cutPaperPercentage;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((logicalName == null) ? 0 : logicalName.hashCode());
		result = prime * result + station;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POSPrinter other = (POSPrinter) obj;
		if (cutPaperPercentage != other.cutPaperPercentage)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (logicalName == null) {
			if (other.logicalName != null)
				return false;
		} else if (!logicalName.equals(other.logicalName))
			return false;
		if (station != other.station)
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "POSPrinter [logicalName=" + logicalName
				+ ", cutPaperPercentage=" + cutPaperPercentage + ", data="
				+ data + ", station=" + station + "]";
	}

	public void printReceiptAndCutPaper() throws JposException {

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
				if (firstPOSPrinterLogicalName.length() == 0){
					firstPOSPrinterLogicalName = entry.getLogicalName();
				}
			}
			
			if (entry.LOGICAL_NAME_PROP_NAME.equals(this.logicalName)){
				posPrinterLogicalName = this.logicalName;
				break;
			}
			
		}
		
		if (posPrinterLogicalName.length() <= 0){
			posPrinterLogicalName = firstPOSPrinterLogicalName;
		}
		
		printReceiptAndCutPaper(posPrinterLogicalName);
	}
	
	private void printReceiptAndCutPaper(String logicalName) throws JposException{
		
			JposServiceConnection connection = JposServiceLoader
					.findService(logicalName);
			connection.connect();
			
			Class<?> posPrinterService;
			try {
				posPrinterService = Class.forName(connection.getService()
						.getClass().getName());
				Object simulatedPOSPrinterService = posPrinterService
						.newInstance();
				Method print = posPrinterService.getDeclaredMethod("printNormal", int.class, String.class);
				print.invoke(simulatedPOSPrinterService, this.station, this.data);
				Method cutPaper = posPrinterService.getDeclaredMethod("cutPaper", int.class);
				cutPaper.invoke(simulatedPOSPrinterService, this.getCutPaperPercentage());
				
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
			
	}

}
