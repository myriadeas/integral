package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.application.Command;

public class ReleaseHoldingCommand implements Command {

	private String itemIdentifier;

	private String itemCategoryCode;

	public ReleaseHoldingCommand(String itemIdentifier, String itemCategoryCode) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.itemCategoryCode = itemCategoryCode;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}

}
