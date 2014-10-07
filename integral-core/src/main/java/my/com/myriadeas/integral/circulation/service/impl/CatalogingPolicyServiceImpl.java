package my.com.myriadeas.integral.circulation.service.impl;

import my.com.myriadeas.integral.circulation.service.CatalogingPolicyService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("catalogingPolicyService")
public class CatalogingPolicyServiceImpl implements CatalogingPolicyService {
	
	private static final String CATALOGING = "cataloging";
		
	@Value("${" + CATALOGING + ".briefTitle}")
	private Boolean briefTitle; 
	
	
	public Boolean getBriefTitle() {
		return briefTitle;
	}
	public void setBriefTitle(Boolean briefTitle) {
		if (briefTitle == null){
			briefTitle = new Boolean(false);
		}
		this.briefTitle = briefTitle;
	}
	
	
	
			
}
