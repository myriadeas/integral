package my.com.myriadeas.integral.assetmanager.infrastructure;

import my.com.myriadeas.integral.assetmanager.domain.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositoryImpl extends JpaRepository<Item, Long>,
		ItemRepository {

}
