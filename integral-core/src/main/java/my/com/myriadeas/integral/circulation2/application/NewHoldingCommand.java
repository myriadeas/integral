package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.application.Command;

public class NewHoldingCommand implements Command {

	private String itemCategoryCode;

	private String itemIdentifier;

	public NewHoldingCommand(String itemCategoryCode, String itemIdentifier) {
		super();
		this.itemCategoryCode = itemCategoryCode;
		this.itemIdentifier = itemIdentifier;
	}

	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

}
