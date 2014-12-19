package my.com.myriadeas.integral.assetmanagement.domain.http;

import java.math.BigDecimal;

public class CreateItemRequest {
	
	private String resourceDescriptorIdentifier;
	
	private BigDecimal foreignCost;
	
	private BigDecimal localCost;

	public String getResourceDescriptorIdentifier() {
		return resourceDescriptorIdentifier;
	}

	public void setResourceDescriptorIdentifier(String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public BigDecimal getForeignCost() {
		return foreignCost;
	}

	public void setForeignCost(BigDecimal foreignCost) {
		this.foreignCost = foreignCost;
	}

	public BigDecimal getLocalCost() {
		return localCost;
	}

	public void setLocalCost(BigDecimal localCost) {
		this.localCost = localCost;
	}


		
}
