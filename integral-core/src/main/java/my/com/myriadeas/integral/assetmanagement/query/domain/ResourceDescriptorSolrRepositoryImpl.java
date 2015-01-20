package my.com.myriadeas.integral.assetmanagement.query.domain;

import java.util.List;

public interface ResourceDescriptorSolrRepositoryImpl extends ResourceDescriptorSolrRepository{
	
   // @Query("title:*?0* AND author:*?0*")
//   public List<ResourceDescriptor> findByQueryAnnotation(String searchTerm);
	
	public List<ResourceDescriptorToListItem> findByTitleContains(String title);
}


