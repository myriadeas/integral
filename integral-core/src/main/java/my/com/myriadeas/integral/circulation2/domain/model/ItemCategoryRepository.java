package my.com.myriadeas.integral.circulation2.domain.model;

public interface ItemCategoryRepository {

	public ItemCategory findByCode(String code);
	
	public ItemCategory save(ItemCategory itemCategory);
}
