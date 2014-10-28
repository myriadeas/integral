package my.com.myriadeas.integral.assetmanager.application.command;


public class ReleaseItemCommand {

	private String itemIdentifier;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public ReleaseItemCommand(String itemIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
	}

}
