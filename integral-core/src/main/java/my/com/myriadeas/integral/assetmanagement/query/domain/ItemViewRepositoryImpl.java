package my.com.myriadeas.integral.assetmanagement.query.domain;

import java.util.List;

public interface ItemViewRepositoryImpl extends ItemViewRepository {

	public List<ItemView> findByResourceDescriptorIdentifier(String resourceDescriptorId);
}
