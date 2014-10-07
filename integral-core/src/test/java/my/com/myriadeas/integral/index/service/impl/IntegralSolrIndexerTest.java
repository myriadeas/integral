package my.com.myriadeas.integral.index.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.config.DevConfig;
import my.com.myriadeas.integral.cataloguing.exception.IntegralSolrIndexerTransformationException;
import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.MarcXmlConverter;
import my.com.myriadeas.integral.data.solr.domain.VuFindMarc;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.marc.Record;
import org.solrmarc.index.SolrIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DevConfig.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegralSolrIndexerTest {

	@Autowired
	private IntegralIndexer indexer;

	@Autowired
	private SolrIndexer solrIndexer;

	@Test
	public void testMapVufindMarc() throws FileNotFoundException, IOException,
			ParseException {
		assertNotNull(indexer);
		assertNotNull(solrIndexer);
		VuFindMarc vufindMarc = new VuFindMarc();
		LinkedHashSet<String> physical = new LinkedHashSet<String>();
		physical.add("864 p. ; 24 cm.");
		String illustrated = "Not Illustrated";
		LinkedHashSet<String> dewey_tens = new LinkedHashSet<String>();
		dewey_tens.add("790 _ Sports, games & entertainment");
		String callnumber_first_code = "P";
		LinkedHashSet<String> collection = new LinkedHashSet<String>();
		collection.add("Catalog");
		String callnumber_a = "PN1992.8.S4";
		LinkedHashSet<String> title_alt = new LinkedHashSet<String>();
		title_alt.add("50 years of television");

		LinkedHashSet<String> publisher = new LinkedHashSet<String>();
		publisher.add("Cornwall Books");
		String id = java.util.UUID.randomUUID().toString();
		String author = "吳承恩";
		String title = "Fifty years of television : a guide to series and pilots, 1937_1988 /";
		String callnumber_subject_code = "PN";
		String allfields = "吳承恩 Fifty years of television : a guide to series and pilots, 1937_1988 / Vincent Terrace. 50 years of television New York : Cornwall Books, c1991. 864 p. ; 24 cm. Includes index. Television pilot programs United States Catalogs. Television serials United States Catalogs.";
		String title_sub = "a guide to series and pilots, 1937_1988 /";

		LinkedHashSet<String> dewey_hundreds = new LinkedHashSet<String>();
		dewey_hundreds.add("700 _ Arts & recreation");
		LinkedHashSet<String> isbn = new LinkedHashSet<String>();
		isbn.add("084534811 :");
		isbn.add("0845348205 (pbk.)");
		String callnumber_label = "PN1992";
		String publishDateSort = "1991";
		LinkedHashSet<String> publishDate = new LinkedHashSet<String>();
		publishDate.add("1991");
		LinkedHashSet<String> institution = new LinkedHashSet<String>();
		institution.add("MyInstitution");
		String recordtype = "marc";
		LinkedHashSet<String> topic = new LinkedHashSet<String>();
		topic.add("Television pilot programs United States Catalogs");
		topic.add("Television serials United States Catalogs");
		LinkedHashSet<String> building = new LinkedHashSet<String>();
		building.add("Library A");
		String callnumber_first = "P _ Language and Literature";
		String title_auth = "Fifty years of television : a guide to series and pilots,  1937_1988 /";
		String dewey_sort = "791.45000000";
		LinkedHashSet<String> geographic_facet = new LinkedHashSet<String>();
		geographic_facet.add("United States");
		String callnumber = "PN1992.8.S4T471991";
		String callnumber_subject = "PN _ General Literature";
		LinkedHashSet<String> topic_facet = new LinkedHashSet<String>();
		topic_facet.add("Television pilot programs");
		topic_facet.add("Television serials");
		LinkedHashSet<String> format = new LinkedHashSet<String>();
		format.add("Book");
		String title_sort = "fifty years of television :a guide to series and pilots, 1937_1988";
		String title_short = "Fifty years of television :";
		LinkedHashSet<String> dewey_full = new LinkedHashSet<String>();
		dewey_full.add("791.45/75/0973");
		LinkedHashSet<String> dewey_ones = new LinkedHashSet<String>();
		dewey_ones.add("791 _ Public performances");
		String fullrecord = "00701nam a2200205   450000500170000000700030001702000390002002000220005904000450008105000260012608200230015210000140017524500930018924600270028226000400030930000210034950000200037065000560039065000490044619920331092212.7ta  a0845348111 :c$29.95 (19.50 U.K.)  a0845348205 (pbk.)  a<organization code>c<organization code>14aPN1992.8.S4bT47 199104a791.45/75/09732191 a吳承恩10aFifty years of television :ba guide to series and pilots, 1937-1988 /cVincent Terrace.1 a50 years of television  aNew York :bCornwall Books,cc1991.  a864 p. ;c24 cm.  aIncludes index. 0aTelevision pilot programszUnited StatesvCatalogs. 0aTelevision serialszUnited StatesvCatalogs.";
		String dewey_raw = "791.45/75/0973";
		vufindMarc.setId(id);
		vufindMarc.setIsbn(isbn);
		vufindMarc.setTopic(topic);
		vufindMarc.setTopic_facet(topic_facet);
		vufindMarc.setFullrecord(fullrecord);
		vufindMarc.setAllfields(allfields);
		vufindMarc.setInstitution(institution);
		vufindMarc.setCollection(collection);
		vufindMarc.setBuilding(building);
		vufindMarc.setFormat(format);
		vufindMarc.setAuthor(author);
		vufindMarc.setTitle(title);
		vufindMarc.setTitle_sort(title_sort);
		vufindMarc.setTitle_sub(title_sub);
		vufindMarc.setTitle_short(title_short);
		vufindMarc.setPhysical(physical);
		vufindMarc.setPublisher(publisher);
		vufindMarc.setPublishDate(publishDate);
		vufindMarc.setPublishDateSort(publishDateSort);
		vufindMarc.setIsbn(new java.util.LinkedHashSet<String>());
		vufindMarc.setCallnumber(callnumber);
		vufindMarc.setCallnumber_a(callnumber_a);
		vufindMarc.setCallnumber_first(callnumber_first);
		vufindMarc.setCallnumber_first_code(callnumber_first_code);
		vufindMarc.setCallnumber_subject(callnumber_subject);
		vufindMarc.setCallnumber_subject_code(callnumber_subject_code);
		vufindMarc.setCallnumber_label(callnumber_label);
		vufindMarc.setDewey_hundreds(dewey_hundreds);
		vufindMarc.setDewey_tens(dewey_tens);
		vufindMarc.setDewey_ones(dewey_ones);
		vufindMarc.setDewey_full(dewey_full);
		vufindMarc.setDewey_sort(dewey_sort);
		vufindMarc.setDewey_raw(dewey_raw);
		vufindMarc.setTitle_alt(title_alt);
		vufindMarc.setTopic(null);
		vufindMarc.setTopic_facet(new java.util.LinkedHashSet<String>());
		vufindMarc.setGeographic_facet(geographic_facet);
		vufindMarc.setIllustrated(illustrated);
		vufindMarc.setRecordtype(recordtype);
		vufindMarc.setTitle_auth(title_auth);
		MarcXmlConverter converter = new MarcXmlConverter();
		String szRecord = "00701nam a2200205   450000500170000000700030001702000390002002000220005904000450008105000260012608200230015210000140017524500930018924600270028226000400030930000210034950000200037065000560039065000490044619920331092212.7ta  a0845348111 :c$29.95 (19.50 U.K.)  a0845348205 (pbk.)  a<organization code>c<organization code>14aPN1992.8.S4bT47 199104a791.45/75/09732191 a吳承恩10aFifty years of television :ba guide to series and pilots, 1937-1988 /cVincent Terrace.1 a50 years of television  aNew York :bCornwall Books,cc1991.  a864 p. ;c24 cm.  aIncludes index. 0aTelevision pilot programszUnited StatesvCatalogs. 0aTelevision serialszUnited StatesvCatalogs.";
		System.out.println("szRecord lenght=" + szRecord.getBytes().length);
		if (szRecord.getBytes().length != 701) {
			// TODO - want to skip the test if running length in maven not 701.
			// encoding problem.
			return;
		}
		Record record = converter.convertMarcToListOfRecords(szRecord).get(0);
		try {
			Map<String, Object> result = solrIndexer.map(record);
			VuFindMarc actual = indexer.mapVufindMarc(record);
			// assertEquals(actual.getId(), result.get("id"));
			assertEquals(actual.getFullrecord(), result.get("fullrecord"));
			assertEquals(actual.getMarc_error(), result.get("marc_error"));
			assertEquals(actual.getAllfields(), result.get("allfields"));
			assertEquals(actual.getAllfields_unstemmed(),
					result.get("allfields_unstemmed"));
			assertEquals(actual.getFulltext(), result.get("fulltext"));
			assertEquals(actual.getFulltext_unstemmed(),
					result.get("fulltext_unstemmed"));
			assertEquals(actual.getSpelling(), result.get("spelling"));
			assertEquals(actual.getSpellingShingle(),
					result.get("spellingShingle"));
			assertEquals(actual.getInstitution().toArray()[0], result.get("institution"));
			assertEquals(actual.getCollection().toArray()[0], result.get("collection"));
			assertEquals(actual.getBuilding().toArray()[0], result.get("building"));
			assertEquals(actual.getLanguage(), result.get("language"));
			assertEquals(actual.getFormat().toArray()[0], result.get("format"));
			assertEquals(actual.getAuthor(), result.get("author"));
			assertEquals(actual.getAuthor_letter(), result.get("author-letter"));
			assertEquals(actual.getAuthorStr(), result.get("authorStr"));
			assertEquals(actual.getTitle(), result.get("title"));
			assertEquals(actual.getTitle_sort(), result.get("title_sort"));
			assertEquals(actual.getTitle_sub(), result.get("title_sub"));
			assertEquals(actual.getTitle_short(), result.get("title_short"));
			assertEquals(actual.getTitle_full(), result.get("title_full"));
			assertEquals(actual.getTitle_full_unstemmed(),
					result.get("title_full_unstemmed"));
			assertEquals(actual.getTitle_fullStr(), result.get("title_fullStr"));
			assertEquals(actual.getTitle_auth(), result.get("title_auth"));
			assertEquals(actual.getPhysical().toArray()[0], result.get("physical"));
			assertEquals(actual.getPublisher().toArray()[0], result.get("publisher"));
			//assertEquals(actual.getPublisherStr().toArray()[0], result.get("publisherStr"));
			assertEquals(actual.getPublishDate().toArray()[0], result.get("publishDate"));
			assertEquals(actual.getPublishDateSort(),
					result.get("publishDateSort"));
			assertEquals(actual.getEdition(), result.get("edition"));
			assertEquals(actual.getDescription(), result.get("description"));
			//assertEquals(actual.getContents().toArray()[0], result.get("contents"));
			//assertEquals(actual.getUrl().toArray()[0], result.get("url"));
			assertEquals(actual.getThumbnail(), result.get("thumbnail"));
			assertEquals(actual.getLccn(), result.get("lccn"));
			//assertEquals(actual.getCtrlnum().toArray()[0], result.get("ctrlnum"));
			assertEquals(actual.getIsbn(), result.get("isbn"));
			//assertEquals(actual.getIssn().toArray()[0], result.get("issn"));
			//assertEquals(actual.getOclc_num().toArray()[0], result.get("oclc_num"));
			assertEquals(actual.getCallnumber(), result.get("callnumber"));
			assertEquals(actual.getCallnumber_a(), result.get("callnumber-a"));
			assertEquals(actual.getCallnumber_first(),
					result.get("callnumber-first"));
			assertEquals(actual.getCallnumber_first_code(),
					result.get("callnumber-first-code"));
			assertEquals(actual.getCallnumber_subject(),
					result.get("callnumber-subject"));
			assertEquals(actual.getCallnumber_subject_code(),
					result.get("callnumber-subject-code"));
			assertEquals(actual.getCallnumber_label(),
					result.get("callnumber-label"));
			assertEquals(actual.getDewey_hundreds().toArray()[0],
					result.get("dewey-hundreds"));
			assertEquals(actual.getDewey_tens().toArray()[0], result.get("dewey-tens"));
			assertEquals(actual.getDewey_ones().toArray()[0], result.get("dewey-ones"));
			assertEquals(actual.getDewey_full().toArray()[0], result.get("dewey-full"));
			assertEquals(actual.getDewey_sort(), result.get("dewey-sort"));
			assertEquals(actual.getDewey_raw(), result.get("dewey-raw"));
			assertEquals(actual.getAuthor2(), result.get("author2"));
			assertEquals(actual.getAuthor2Str(), result.get("author2Str"));
			assertEquals(actual.getAuthor2_role(), result.get("author2-role"));
			assertEquals(actual.getAuthor_fuller(), result.get("author_fuller"));
			//assertEquals(actual.getAuthor_additional().toArray()[0],
			//		result.get("author_additional"));
			/*-assertEquals(actual.getAuthor_additionalStr().toArray()[0],
					result.get("author_additionalStr"));
			assertEquals(actual.getTitle_old().toArray()[0], result.get("title_old"));
			assertEquals(actual.getTitle_new().toArray()[0], result.get("title_new"));
			*/
			assertEquals(actual.getTitle_alt().toArray()[0], result.get("title_alt"));
			//assertEquals(actual.getDateSpan().toArray()[0], result.get("dateSpan"));
			//assertEquals(actual.getSeries().toArray()[0], result.get("series"));
			//assertEquals(actual.getSeries2().toArray()[0], result.get("series2"));
			assertEquals(actual.getTopic(), result.get("topic"));
			assertEquals(actual.getTopic_unstemmed(),
					result.get("topic_unstemmed"));
			assertEquals(actual.getTopic_facet(), result.get("topic_facet"));
			//assertEquals(actual.getTopic_browse().toArray()[0], result.get("topic_browse"));
			//assertEquals(actual.getAuthor_browse().toArray()[0], result.get("author_browse"));
			//assertEquals(actual.getGenre().toArray()[0], result.get("genre"));
			//assertEquals(actual.getGenre_facet().toArray()[0], result.get("genre_facet"));
			//assertEquals(actual.getGeographic().toArray()[0], result.get("geographic"));
			//assertEquals(actual.getGeographic_facet().toArray()[0],
			//		result.get("geographic_facet"));
			//assertEquals(actual.getEra().toArray()[0], result.get("era"));
			//assertEquals(actual.getEra_facet().toArray()[0], result.get("era_facet"));
			assertEquals(actual.getIllustrated(), result.get("illustrated"));
			assertEquals(actual.getLong_lat(), result.get("long_lat"));
			assertEquals(actual.getRecordtype(), result.get("recordtype"));
			assertEquals(actual.getFirst_indexed(), result.get("first_indexed"));
			assertEquals(actual.getLast_indexed(), result.get("last_indexed"));
		} catch (IntegralSolrIndexerTransformationException e) {
			e.printStackTrace();
			fail("should not catch exception");
		}
	}

}
