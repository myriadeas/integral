package my.com.myriadeas.integral.circulation2.interfaces;

public class NewHoldingRequestDTO {
	
	private String itemIdentifier;
	
	private String itemCategoryCode;

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

	public NewHoldingRequestDTO(String itemIdentifier, String itemCategoryCode) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.itemCategoryCode = itemCategoryCode;
	}
	
	

}
