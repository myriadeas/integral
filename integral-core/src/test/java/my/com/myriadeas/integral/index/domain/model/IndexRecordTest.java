package my.com.myriadeas.integral.index.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.index.domain.service.Indexer;

import org.junit.Before;
import org.junit.Test;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;

public class IndexRecordTest {
	private IndexRecord indexRecord;
	private String marc = "01263cam a2200337 a 4500001000800000005001700008008004100025906004500066010001700111020002700128035002600155040003100181043001200212050002500224066000700249245008300256250002200339260010300361300002500464500006900489651003600558700002100594700002300615740003800638880007000676880002400746880009100770880002300861880002300884985001800907520483020070825203548.0940207s1990    cc            000 0 chi    a7bcbccorignewduencipf19gy-rlinjack  a   92200709   a7805282439 :cRMBY5.50  a(CStRLIN)DCLP94-B2309  aDLC-RcDLC-RdCStRLINdDLC  aa-cc---00aDS721b.C572467 1990  c$1006880-01aZhongguo chuan tong wen hua qi guan /cGu Tai, Zhang Li deng bian zhu.  6880-02aDi 1 ban.  6880-03aChangchun Shi :bJilin wen shi chu ban she :bJilin sheng xin hua shu dian fa xing,c1990.  a13, 443 p. ;c19 cm.  aColophon title also in pinyin: Zhongguo chuantong wenhua qiguan. 0aChinaxCivilizationxAnecdotes.1 6880-04aGu, Tai.1 6880-05aZhang, Li.0 aZhongguo chuantong wenhua qiguan.006245-01/$1a中国传统文化奇观 /c顾太,　张利等编著.  6250-02/$1a第1版.  6260-03/$1a长春市　:b吉林文史出版社 :b吉林省新华书店发行,c1990.1 6700-04/$1a顾太.1 6700-05/$1a张利.  aspacingreload";
	private String resourceDescriptorId = "0000000001";

	/**
	 * Sets up the test fixture. (Called before every test case method.)
	 */
	@Before
	public void setUp() {
		indexRecord = new IndexRecord(marc, resourceDescriptorId);
		MarcFactory marcFactory = MarcFactory.newInstance();
		indexRecord.setMarcFactory(marcFactory);
	}

	@Test
	/**
	 * Reading this - http://martinfowler.com/articles/mocksArentStubs.html
	 */
	public void testGetMarcRecord() {
		Record record = indexRecord.getMarcRecord();
		assertNotNull(record);
		assertEquals(record.getControlNumber(), resourceDescriptorId);
	}

	@Test
	public void testGetVuFindMarc() {
		final VuFindMarc vufindMarc = new VuFindMarc();
		Indexer indexerStub = new Indexer() {
			@Override
			public VuFindMarc mapVufindMarc(Record record) {
				return vufindMarc;
			}
		};
		indexRecord.setIndexer(indexerStub);
		assertEquals(vufindMarc, indexRecord.getVuFindMarc());
	}

	@Test
	public void testIndex() {
		String indexMarc = "01263cam a2200337 a 4500001000800000005001700008008004100025906004500066010001700111020002700128035002600155040003100181043001200212050002500224066000700249245008300256250002200339260010300361300002500464500006900489651003600558700002100594700002300615740003800638880007000676880002400746880009100770880002300861880002300884985001800907520483020070825203548.0940207s1990    cc            000 0 chi    a7bcbccorignewduencipf19gy-rlinjack  a   92200709   a7805282439 :cRMBY5.50  a(CStRLIN)DCLP94-B2309  aDLC-RcDLC-RdCStRLINdDLC  aa-cc---00aDS721b.C572467 1990  c$1006880-01aZhongguo chuan tong wen hua qi guan /cGu Tai, Zhang Li deng bian zhu.  6880-02aDi 1 ban.  6880-03aChangchun Shi :bJilin wen shi chu ban she :bJilin sheng xin hua shu dian fa xing,c1990.  a13, 443 p. ;c19 cm.  aColophon title also in pinyin: Zhongguo chuantong wenhua qiguan. 0aChinaxCivilizationxAnecdotes.1 6880-04aGu, Tai.1 6880-05aZhang, Li.0 aZhongguo chuantong wenhua qiguan.006245-01/$1a中国传统文化奇观 /c顾太,　张利等编著.  6250-02/$1a第1版.  6260-03/$1a长春市　:b吉林文史出版社 :b吉林省新华书店发行,c1990.1 6700-04/$1a顾太.1 6700-05/$1a张利.  bspacingreload";
		final VuFindMarc vufindMarc = new VuFindMarc();
		Indexer indexerStub = new Indexer() {
			@Override
			public VuFindMarc mapVufindMarc(Record record) {
				return vufindMarc;
			}
		};
		indexRecord.setIndexer(indexerStub);
		VuFindMarcRepositoryStub repository = new VuFindMarcRepositoryStub();
		((NewIso) IndexStatus.NEW.getOperations()).setRepository(repository);
		Map<String, DomainEvent> events = indexRecord.index(indexMarc);

		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		DomainEvent expectedEvent = new IndexRecordIndexed(indexMarc,
				resourceDescriptorId);
		expectedEvents.put("indexRecordIndexed", expectedEvent);
		int expectedCount = 1;
		assertEquals(expectedCount, repository.count());
		assertEquals(expectedEvents, events);
		assertEquals(indexRecord.getStatus(), IndexStatus.INDEX);
	}

	@Test
	public void testUnindex() {
		indexRecord = new IndexRecord(marc, resourceDescriptorId,
				IndexStatus.INDEX);
		MarcFactory marcFactory = MarcFactory.newInstance();
		indexRecord.setMarcFactory(marcFactory);
		final VuFindMarc vufindMarc = new VuFindMarc();
		Indexer indexerStub = new Indexer() {
			@Override
			public VuFindMarc mapVufindMarc(Record record) {
				return vufindMarc;
			}
		};
		indexRecord.setIndexer(indexerStub);
		VuFindMarcRepository repository = new VuFindMarcRepositoryStub();
		((IndexIso) IndexStatus.INDEX.getOperations())
				.setRepository(repository);
		Map<String, DomainEvent> events = indexRecord.unindex();
		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		DomainEvent expectedEvent = new IndexRecordUnindexed(
				resourceDescriptorId);
		expectedEvents.put("indexRecordUnindexed", expectedEvent);
		assertEquals(expectedEvents, events);
		assertEquals(indexRecord.getStatus(), IndexStatus.UNINDEX);
	}
}
