package my.com.myriadeas.integral.item.query.solr;

import java.util.List;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorToListItem;

public interface ResourceDescriptorSolrRepositoryImpl extends ResourceDescriptorSolrRepository{
	
   // @Query("title:*?0* AND author:*?0*")
//   public List<ResourceDescriptor> findByQueryAnnotation(String searchTerm);
	
	public List<ResourceDescriptorToListItem> findByTitle(String title);
}


