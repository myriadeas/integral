package my.com.myriadeas.integral.assetmanagement.query.domain;

import java.util.List;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

@NoRepositoryBean
public interface ResourceDescriptorSolrRepository extends SolrCrudRepository<ItemView, String>{
	
   // @Query("title:*?0* AND author:*?0*")
//   public List<ResourceDescriptor> findByQueryAnnotation(String searchTerm);
	
	public List<ResourceDescriptorToListItem> findByTitleContains(String title);
}


