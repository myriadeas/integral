package my.com.myriadeas.integral.item.query.solr;

import java.util.List;

import javax.annotation.Resource;

import my.com.myriadeas.integral.item.query.domain.ResourceDescriptorSolr;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDescriptorSolrRepositoryImpl implements
		CustomResourceDescriptorSolrRepository {

	@Resource
	private SolrTemplate solrTemplate;

	@Override
	public List<ResourceDescriptorSolr> searchByTitle(String title) {

		Criteria conditions = new Criteria("title").is(title);
		SimpleQuery search = new SimpleQuery(conditions);

		Page results = solrTemplate.queryForPage(search,
				ResourceDescriptorSolr.class);
		return results.getContent();
	}

	@Override
	public List<ResourceDescriptorSolr> searchByAuthor(String author) {
		Criteria conditions = new Criteria("author").is(author);
		SimpleQuery search = new SimpleQuery(conditions);

		Page results = solrTemplate.queryForPage(search,
				ResourceDescriptorSolr.class);
		return results.getContent();
	}

	@Override
	public List<ResourceDescriptorSolr> searchByIsbn(String isbn) {
		Criteria conditions = new Criteria("isbn").is(isbn);
		SimpleQuery search = new SimpleQuery(conditions);

		Page results = solrTemplate.queryForPage(search,
				ResourceDescriptorSolr.class);
		return results.getContent();
	}

	@Override
	public List<ResourceDescriptorSolr> searchByTitleAuthorIsbn(String title,
			String author, String isbn) {
		Criteria conditions = new Criteria("title").is(title).and(
				new Criteria("author").is(author).and(
						new Criteria("isbn").is(isbn)));
		SimpleQuery search = new SimpleQuery(conditions);

		Page results = solrTemplate.queryForPage(search,
				ResourceDescriptorSolr.class);
		return results.getContent();
	}

	@Override
	public List<ResourceDescriptorSolr> searchByAvailableInput(String title,
			String author, String isbn) {

		Criteria conditions = null;

		if (StringUtils.isBlank(title)) {
			if (StringUtils.isBlank(author)) {
				if (StringUtils.isBlank(isbn)) {
					return null;
				} else {
					return searchByIsbn(isbn);
				}

			} else {
				if (StringUtils.isBlank(isbn)) {
					return searchByAuthor(author);
				} else {
					conditions = new Criteria("author").is(author).and(
							new Criteria("isbn").is(isbn));
				}
			}

		} else {
			if (StringUtils.isBlank(author)) {
				if (StringUtils.isBlank(isbn)) {
					return searchByTitle(title);
				} else {
					conditions = new Criteria("title").is(title).and(
							new Criteria("isbn").is(isbn));
				}
			} else {
				if (StringUtils.isBlank(isbn)) {
					conditions = new Criteria("title").is(title).and(
							new Criteria("author").is(author));
				} else {
					return searchByTitleAuthorIsbn(title, author, isbn);
				}

			}
		}

		SimpleQuery search = new SimpleQuery(conditions);

		Page results = solrTemplate.queryForPage(search,
				ResourceDescriptorSolr.class);
		return results.getContent();

	}

	@Override
	public ResourceDescriptorSolr searchById(String id) {
		Criteria conditions = new Criteria("id").is(id);
		SimpleQuery search = new SimpleQuery(conditions);

		ResourceDescriptorSolr result = solrTemplate.queryForObject(search,
				ResourceDescriptorSolr.class);
		return result;
	}

}
