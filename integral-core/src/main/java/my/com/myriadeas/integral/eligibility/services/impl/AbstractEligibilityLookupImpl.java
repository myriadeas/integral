package my.com.myriadeas.integral.eligibility.services.impl;

import my.com.myriadeas.integral.bean.ReloadableBean;
import my.com.myriadeas.integral.eligibility.EligibilityTableFactory;
import my.com.myriadeas.integral.eligibility.FirstMatchLookupStrategyImpl;
import my.com.myriadeas.integral.eligibility.LookupStrategy;
import my.com.myriadeas.integral.eligibility.LookupTable;

public abstract class AbstractEligibilityLookupImpl implements ReloadableBean {

	LookupStrategy lookupStrategy;

	EligibilityTableFactory lookupTableFactoryBean;

	private void setLookupTable(LookupTable lookupTable) {
		lookupStrategy = new FirstMatchLookupStrategyImpl(lookupTable);
	}

	public void setLookupTableFactoryBean(
			EligibilityTableFactory lookupTableFactoryBean) {
		this.lookupTableFactoryBean = lookupTableFactoryBean;
		this.setLookupTable(lookupTableFactoryBean.getLookupTable());
	}

	public void reload() {
		this.setLookupTable(lookupTableFactoryBean.getLookupTable());
	}
}
