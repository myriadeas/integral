package my.com.myriadeas.integral.javapos;

import java.util.Map;

import jpos.JposException;
import jpos.services.EventCallbacks;
import jpos.services.POSPrinterService111;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IntegralPOSPrinterService implements POSPrinterService111 {
	static final Log logger = LogFactory
			.getLog(IntegralPOSPrinterService.class);

	protected boolean asyncMode = true;
	protected int slpLineChars;
	protected int slpLineHeight;
	protected int slpLineSpacing;
	protected int characterSet;
	protected boolean flagWhenIdle;
	protected boolean jrnLetterQuality;
	protected int jrnLineChars;
	protected int jrnLineHeight;
	protected int jrnLineSpacing;
	protected int mapMode;
	protected boolean recLetterQuality;
	protected int recLineChars;
	protected int recLineHeight;
	protected int recLineSpacing;
	protected int rotateSpecial;
	protected boolean slpLetterQuality;
	protected Map<Integer, Object> inMemoryBitmaps;

	public void reset() {
	}

	public void cutPaper(int i) throws JposException {
		System.out.println("\n\n" + i
				+ "\n\n--------------- cut here ---------------\n\n");
	}

	@Override
	public void printMemoryBitmap(int station, byte[] data, int type,
			int width, int alignment) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getCapCompareFirmwareVersion() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapConcurrentPageMode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecPageMode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpPageMode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapUpdateFirmware() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPageModeArea() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageModeDescriptor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPageModeHorizontalPosition() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPageModeHorizontalPosition(int position)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPageModePrintArea() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPageModePrintArea(String area) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPageModePrintDirection() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPageModePrintDirection(int direction) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPageModeStation() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPageModeStation(int station) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPageModeVerticalPosition() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPageModeVerticalPosition(int position) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearPrintArea() throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void compareFirmwareVersion(String firmwareFileName, int[] result)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void pageModePrint(int control) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFirmware(String firmwareFileName) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getCapStatisticsReporting() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapUpdateStatistics() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetStatistics(String statisticsBuffer) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveStatistics(String[] statisticsBuffer)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatistics(String statisticsBuffer) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getCapMapCharacterSet() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getMapCharacterSet() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMapCharacterSet(boolean mapCharacterSet)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRecBitmapRotationList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSlpBitmapRotationList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCapJrnCartridgeSensor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapJrnColor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapRecCartridgeSensor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapRecColor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapRecMarkFeed() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCapSlpBothSidesPrint() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCapSlpCartridgeSensor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapSlpColor() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartridgeNotify() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCartridgeNotify(int notify) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getJrnCartridgeState() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getJrnCurrentCartridge() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJrnCurrentCartridge(int cartridge) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRecCartridgeState() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecCurrentCartridge() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRecCurrentCartridge(int cartridge) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlpCartridgeState() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSlpCurrentCartridge() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSlpCurrentCartridge(int cartridge) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlpPrintSide() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changePrintSide(int side) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void markFeed(int type) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCapPowerReporting() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPowerState() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapCharacterSet() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCapConcurrentJrnRec() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapConcurrentJrnSlp() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapConcurrentRecSlp() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapCoverSensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrn2Color() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnBold() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnDwide() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnDwideDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnEmptySensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnItalic() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnNearEndSensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnPresent() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapJrnUnderline() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRec2Color() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecBarCode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecBitmap() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecBold() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecDwide() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecDwideDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecEmptySensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecItalic() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecLeft90() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecNearEndSensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecPapercut() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecPresent() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecRight90() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecRotate180() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecStamp() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapRecUnderline() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlp2Color() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpBarCode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpBitmap() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpBold() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpDwide() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpDwideDhigh() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpEmptySensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpFullslip() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpItalic() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpLeft90() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpNearEndSensor() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpPresent() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpRight90() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpRotate180() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapSlpUnderline() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCapTransaction() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAsyncMode() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAsyncMode(boolean asyncMode) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCharacterSet() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCharacterSet(int characterSet) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCharacterSetList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getCoverOpen() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getErrorLevel() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getErrorStation() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getErrorString() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFlagWhenIdle() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlagWhenIdle(boolean flagWhenIdle) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFontTypefaceList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getJrnEmpty() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getJrnLetterQuality() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJrnLetterQuality(boolean jrnLetterQuality)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getJrnLineChars() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJrnLineChars(int jrnLineChars) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getJrnLineCharsList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getJrnLineHeight() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJrnLineHeight(int jrnLineHeight) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getJrnLineSpacing() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJrnLineSpacing(int jrnLineSpacing) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getJrnLineWidth() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getJrnNearEnd() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMapMode() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMapMode(int mapMode) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOutputID() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRecBarCodeRotationList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getRecEmpty() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRecLetterQuality() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRecLetterQuality(boolean recLetterQuality)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRecLineChars() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRecLineChars(int recLineChars) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRecLineCharsList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRecLineHeight() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRecLineHeight(int recLineHeight) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRecLineSpacing() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRecLineSpacing(int recLineSpacing) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRecLinesToPaperCut() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecLineWidth() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getRecNearEnd() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRecSidewaysMaxChars() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecSidewaysMaxLines() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRotateSpecial() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRotateSpecial(int rotateSpecial) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSlpBarCodeRotationList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSlpEmpty() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSlpLetterQuality() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSlpLetterQuality(boolean recLetterQuality)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlpLineChars() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSlpLineChars(int recLineChars) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSlpLineCharsList() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSlpLineHeight() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSlpLineHeight(int recLineHeight) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlpLinesNearEndToEnd() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSlpLineSpacing() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSlpLineSpacing(int recLineSpacing) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSlpLineWidth() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSlpMaxLines() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getSlpNearEnd() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSlpSidewaysMaxChars() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSlpSidewaysMaxLines() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void beginInsertion(int timeout) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginRemoval(int timeout) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearOutput() throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endInsertion() throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endRemoval() throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printBarCode(int station, String data, int symbology,
			int height, int width, int alignment, int textPosition)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printBitmap(int station, String fileName, int width,
			int alignment) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printImmediate(int station, String data) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printNormal(int station, String data) throws JposException {

		System.out.println("\n\n Printing at station: " + station);
		System.out.println("\n\n" + data);
	}

	@Override
	public void printTwoNormal(int stations, String data1, String data2)
			throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotatePrint(int station, int rotation) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBitmap(int bitmapNumber, int station, String fileName,
			int width, int alignment) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLogo(int location, String data) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void transactionPrint(int station, int control) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateData(int station, String data) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCheckHealthText() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceServiceDescription() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhysicalDeviceDescription() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhysicalDeviceName() throws JposException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkHealth(int level) throws JposException {
		// TODO Auto-generated method stub

	}

	@Override
	public void directIO(int command, int[] data, Object object)
			throws JposException {
		// TODO Auto-generated method stub

	}

	public Map<Integer, Object> getInMemoryBitmaps() {
		// TODO Auto-generated method stub
		return inMemoryBitmaps;
	}

	@Override
	public int getPowerNotify() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPowerNotify(int powerNotify) throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getClaimed() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDeviceEnabled() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDeviceEnabled(boolean deviceEnabled) throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeviceServiceVersion() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getFreezeEvents() throws JposException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFreezeEvents(boolean freezeEvents) throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getState() throws JposException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void claim(int timeout) throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(String logicalName, EventCallbacks cb)
			throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() throws JposException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInstance() throws JposException {
		// TODO Auto-generated method stub
		
	}

}
