package my.com.myriadeas.integral.assetmanager.application.command;

import java.math.BigDecimal;

public class CreateItemCommand {
	
	private String itemIdentifier;
	
	private String resourceDescriptorIdentifier;
	
	private BigDecimal foreignPrice;
	
	private BigDecimal localPrice;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getResourceDescriptorIdentifier() {
		return resourceDescriptorIdentifier;
	}

	public void setResourceDescriptorIdentifier(String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public BigDecimal getForeignPrice() {
		return foreignPrice;
	}

	public void setForeignPrice(BigDecimal foreignPrice) {
		this.foreignPrice = foreignPrice;
	}

	public BigDecimal getLocalPrice() {
		return localPrice;
	}

	public void setLocalPrice(BigDecimal localPrice) {
		this.localPrice = localPrice;
	}

	public CreateItemCommand(String itemIdentifier,
			String resourceDescriptorIdentifier, BigDecimal foreignPrice,
			BigDecimal localPrice) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.foreignPrice = foreignPrice;
		this.localPrice = localPrice;
	}


	
	

}
