package my.com.myriadeas.integral.item.query.solr;

import my.com.myriadeas.integral.item.query.domain.ItemView;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.SolrCrudRepository;

@NoRepositoryBean
public interface ResourceDescriptorSolrRepository extends SolrCrudRepository<ItemView, String>{
}


