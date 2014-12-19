package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.core.interfaces.DTO;

public class ReleaseHoldingRequestDTO implements DTO {

	private String itemIdentifier;

	private String itemCategoryCode;

	public ReleaseHoldingRequestDTO() {

	}

	public ReleaseHoldingRequestDTO(String itemIdentifier,
			String itemCategoryCode) {
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
