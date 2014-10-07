package my.com.myriadeas.integral.cataloguing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.index.service.impl.IntegralSolrIndexer;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.StringToRecord;
import my.com.myriadeas.integral.data.solr.domain.VuFindMarc;

import org.marc4j.marc.Record;
import org.solrmarc.index.SolrIndexer;
import org.solrmarc.index.VuFindIndexer;
import org.springframework.context.annotation.Bean;

public class CatalogueTest {

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ParseException {
		CatalogueTest catalogueTest = new CatalogueTest();
		IntegralIndexer indexer = catalogueTest.integralIndexer();
		String marc = "01263cam a2200337 a 4500001000800000005001700008008004100025906004500066010001700111020002700128035002600155040003100181043001200212050002500224066000700249245008300256250002200339260010300361300002500464500006900489651003600558700002100594700002300615740003800638880007000676880002400746880009100770880002300861880002300884985001800907520483020070825203548.0940207s1990    cc            000 0 chi    a7bcbccorignewduencipf19gy-rlinjack  a   92200709   a7805282439 :cRMBY5.50  a(CStRLIN)DCLP94-B2309  aDLC-RcDLC-RdCStRLINdDLC  aa-cc---00aDS721b.C572467 1990  c$1006880-01aZhongguo chuan tong wen hua qi guan /cGu Tai, Zhang Li deng bian zhu.  6880-02aDi 1 ban.  6880-03aChangchun Shi :bJilin wen shi chu ban she :bJilin sheng xin hua shu dian fa xing,c1990.  a13, 443 p. ;c19 cm.  aColophon title also in pinyin: Zhongguo chuantong wenhua qiguan. 0aChinaxCivilizationxAnecdotes.1 6880-04aGu, Tai.1 6880-05aZhang, Li.0 aZhongguo chuantong wenhua qiguan.006245-01/$1a中国传统文化奇观 /c顾太,　张利等编著.  6250-02/$1a第1版.  6260-03/$1a长春市　:b吉林文史出版社 :b吉林省新华书店发行,c1990.1 6700-04/$1a顾太.1 6700-05/$1a张利.  aspacingreload";
		StringToRecord stringToRecord = new StringToRecord();
		Record record = stringToRecord.convert(marc);
		VuFindMarc vufindMarc = indexer.mapVufindMarc(record);
	}

	public IntegralIndexer integralIndexer() throws FileNotFoundException,
			IOException, ParseException {
		return new IntegralSolrIndexer(solrIndexer());
	}

	@Bean
	public SolrIndexer solrIndexer() throws FileNotFoundException, IOException,
			ParseException {
		SolrIndexer indexer = new VuFindIndexer(
				"src/test/resources/my/com/myriadeas/integral/index/config/marc.properties, src/test/resources/my/com/myriadeas/integral/index/config/marc_local.properties",
				new String[] {
						"src/test/resources/my/com/myriadeas/integral/index/config",
						"src/test/resources/my/com/myriadeas/integral/index/config/translation_maps",
						"src/test/resources/my/com/myriadeas/integral/index/config/index_scripts" });

		return indexer;
	}

}
