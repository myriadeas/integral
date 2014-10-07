package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

@Entity
public class ItemCategory extends AbstractCategoryDomain {

	private static final long serialVersionUID = 1L;

	public ItemCategory() {
	}

	public ItemCategory(String code, String description) {
		super(code, description);
	}

	@Override
	public String getCriteriaCode() {
		return ITEM_CATEGORY;
	}
}