package my.com.myriadeas.integral.assetmanagement.query.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemViewRepository extends JpaRepository<ItemView, Long> {

	public List<ItemView> findByResourceDescriptorId(String resourceDescriptorId);
}
