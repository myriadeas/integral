package my.com.myriadeas.integral.data.populator;


public interface CheckoutDatabasePopulatorIntf extends
		DatabaseInitializingBean, PatronData, PatronEligibilityData, ItemData,
		PatronItemEligibilityData, CirculationTransactionData {

	public void init();

}
