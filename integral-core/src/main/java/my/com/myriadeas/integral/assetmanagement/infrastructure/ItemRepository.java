package my.com.myriadeas.integral.assetmanagement.infrastructure;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemRepository extends JpaRepository<Item, Long> {

}