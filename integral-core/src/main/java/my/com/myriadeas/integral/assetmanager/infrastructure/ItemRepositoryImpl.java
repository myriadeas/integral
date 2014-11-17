package my.com.myriadeas.integral.assetmanager.infrastructure;

import java.util.List;

import my.com.myriadeas.integral.assetmanager.domain.model.Item;

import org.springframework.data.repository.query.Param;

public interface ItemRepositoryImpl extends ItemRepository {

	public Item findById(@Param("search") Long id);

	public Item findByItemIdentifier(@Param("search") String itemIdentifier);

	public List<Item> findByResourceDescriptorIdentifier(
			@Param("search") String resourceDescriptorIdentifier);

}
