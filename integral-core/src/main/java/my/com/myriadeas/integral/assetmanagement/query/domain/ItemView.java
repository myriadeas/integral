package my.com.myriadeas.integral.assetmanagement.query.domain;

public class ItemView {

	private String itemIdentifier;

	private String resourceDescriptorIdentifier;

	private String title;

	public ItemView() {
		super();
	}

	public ItemView(String itemIdentifier, String resourceDescriptorIdentifier,
			String title) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
		this.title = title;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getResourceDescriptorIdentifier() {
		return resourceDescriptorIdentifier;
	}

	public void setResourceDescriptorIdentifier(
			String resourceDescriptorIdentifier) {
		this.resourceDescriptorIdentifier = resourceDescriptorIdentifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
