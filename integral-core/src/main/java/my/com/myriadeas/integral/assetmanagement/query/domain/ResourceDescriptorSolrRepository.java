package my.com.myriadeas.integral.assetmanagement.query.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.SolrCrudRepository;

@NoRepositoryBean
public interface ResourceDescriptorSolrRepository extends SolrCrudRepository<ItemView, String>{
}


