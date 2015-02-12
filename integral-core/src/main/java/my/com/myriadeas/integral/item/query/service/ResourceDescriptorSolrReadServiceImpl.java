package my.com.myriadeas.integral.item.query.service;

import java.util.List;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;
import my.com.myriadeas.integral.item.query.solr.ResourceDescriptorSolrRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("resourceDescriptorSolrReadService")
public class ResourceDescriptorSolrReadServiceImpl implements
		ResourceDescriptorSolrReadService {

	private static final Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorSolrReadServiceImpl.class);

	@Autowired
	private ResourceDescriptorSolrRepositoryImpl resourceDescriptorSolrRepository;

	@Override
	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByTitle(
			String title) {	
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByTitle(title);
		return resourceDescriptorSolrList;
	}

	@Override
	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByAuthor(
			String author) {
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByAuthor(author);
		return resourceDescriptorSolrList;
	}

	@Override
	public List<ResourceDescriptorSolr> getResourceDescriptorSolrListByIsbn(
			String isbn) {
		List<ResourceDescriptorSolr> resourceDescriptorSolrList = resourceDescriptorSolrRepository
				.searchByIsbn(isbn);
		return resourceDescriptorSolrList;
	}

	@Override
	public ResourceDescriptorSolr getResourceDescriptorSolrById(String id) {
		ResourceDescriptorSolr resourceDescriptorSolr = resourceDescriptorSolrRepository.searchById(id);
		return resourceDescriptorSolr;
	}

}
