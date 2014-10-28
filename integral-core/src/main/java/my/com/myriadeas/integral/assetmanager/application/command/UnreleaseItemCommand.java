package my.com.myriadeas.integral.assetmanager.application.command;


public class UnreleaseItemCommand {
	
	private String itemIdentifier;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public UnreleaseItemCommand(String itemIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
	}

}
