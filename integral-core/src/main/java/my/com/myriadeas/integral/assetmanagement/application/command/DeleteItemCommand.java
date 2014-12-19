package my.com.myriadeas.integral.assetmanagement.application.command;


public class DeleteItemCommand {
	
	private String itemIdentifier;

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public DeleteItemCommand(String itemIdentifier) {
		super();
		this.itemIdentifier = itemIdentifier;
	}


}
