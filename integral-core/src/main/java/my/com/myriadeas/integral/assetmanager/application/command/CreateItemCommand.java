package my.com.myriadeas.integral.assetmanager.application.command;

import java.math.BigDecimal;

public class CreateItemCommand {
	
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

	public CreateItemCommand(String resourceDescriptorIdentifier, BigDecimal foreignCost,
			BigDecimal localCost) {
		super();
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.foreignCost = foreignCost;
		this.localCost = localCost;
	}


	
	

}
