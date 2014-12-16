package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.application.Command;

public class NewHoldingCommand implements Command {

	private String itemIdentifier;

	public NewHoldingCommand(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

}
