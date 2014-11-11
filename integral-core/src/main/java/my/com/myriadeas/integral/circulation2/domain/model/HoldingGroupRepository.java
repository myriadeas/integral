package my.com.myriadeas.integral.circulation2.domain.model;

public interface HoldingGroupRepository {

	public HoldingGroup findByItemCategory(ItemCategory itemCategory);

	public HoldingGroup save(HoldingGroup holdingGroup);
}
