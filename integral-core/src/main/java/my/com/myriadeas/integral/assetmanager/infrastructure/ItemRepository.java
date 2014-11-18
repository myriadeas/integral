package my.com.myriadeas.integral.assetmanager.infrastructure;

import my.com.myriadeas.integral.assetmanager.domain.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemRepository extends JpaRepository<Item, Long> {


}