package my.com.myriadeas.integral.cataloguing2.rest.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.StringToRecord;
import my.com.myriadeas.integral.cataloguing2.config.CataloguingConfig;
import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;
import my.com.myriadeas.integral.cataloguing2.infrastructure.ResourceDescriptorRepositoryImpl;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CataloguingConfig.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class ResourceDescriptorRestServiceTest extends CamelTestSupport {

	@Autowired
	@Qualifier("cataloguing2ServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:welcome", context = "cataloguing2ServiceRoute")
	protected ProducerTemplate template;

	@EndpointInject(uri = "mock:cataloguing2", context = "cataloguing2ServiceRoute")
	protected MockEndpoint mockEndpoint;

	@Autowired
	ResourceDescriptorRepositoryImpl resourceDescriptorRepositoryImpl;

	String marc = "01263cam a2200337 a 4500001000800000005001700008008004100025906004500066010001700111020002700128035002600155040003100181043001200212050002500224066000700249245008300256250002200339260010300361300002500464500006900489651003600558700002100594700002300615740003800638880007000676880002400746880009100770880002300861880002300884985001800907520483020070825203548.0940207s1990    cc            000 0 chi    a7bcbccorignewduencipf19gy-rlinjack  a   92200709   a7805282439 :cRMBY5.50  a(CStRLIN)DCLP94-B2309  aDLC-RcDLC-RdCStRLINdDLC  aa-cc---00aDS721b.C572467 1990  c$1006880-01aZhongguo chuan tong wen hua qi guan /cGu Tai, Zhang Li deng bian zhu.  6880-02aDi 1 ban.  6880-03aChangchun Shi :bJilin wen shi chu ban she :bJilin sheng xin hua shu dian fa xing,c1990.  a13, 443 p. ;c19 cm.  aColophon title also in pinyin: Zhongguo chuantong wenhua qiguan. 0aChinaxCivilizationxAnecdotes.1 6880-04aGu, Tai.1 6880-05aZhang, Li.0 aZhongguo chuantong wenhua qiguan.006245-01/$1a中国传统文化奇观 /c顾太,　张利等编著.  6250-02/$1a第1版.  6260-03/$1a长春市　:b吉林文史出版社 :b吉林省新华书店发行,c1990.1 6700-04/$1a顾太.1 6700-05/$1a张利.  aspacingreload";
	StringToRecord stringToRecord = new StringToRecord();
	Record record = stringToRecord.convert(marc);

	@Test
	public void testMarker() throws Exception {
	}

	@Test
	public void testCreateResourceDescriptor() throws InterruptedException {

		MockEndpoint mockAddRecord = getMockEndpoint("mock:cataloguing2.create");

		InputStream is;
		try {
			is = new FileInputStream("data/xiyouji.json");
			String response = template.requestBody(
					"direct://cataloguing2.create", is, String.class);
			mockAddRecord.assertIsSatisfied();
			mockAddRecord.expectedMessageCount(1);
			System.out.println("Response: " + response);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testCreateResourceDescriptorByRecord()
			throws InterruptedException {

		MockEndpoint mockAddRecord = getMockEndpoint("mock:cataloguing2.create");
		InputStream is;
		try {
			is = new FileInputStream("data/xiyouji.json");
			String response = template.requestBody(
					"direct://cataloguing2.create", is, String.class);
			mockAddRecord.assertIsSatisfied();
			mockAddRecord.expectedMessageCount(1);
			System.out.println("Response: " + response);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testUpdateResourceDescriptor() throws InterruptedException,
			FileNotFoundException {

		InputStream is = new FileInputStream("data/xiyouji.json");
		Long id = template.requestBody("direct://cataloguing2.create", is,
				Long.class);

		MockEndpoint mockUpdateRecord = getMockEndpoint("mock:cataloguing2.update");
		is = new FileInputStream("data/xiyouji.json");
		ResourceDescriptor rd = resourceDescriptorRepositoryImpl.findOne(id);
		template.sendBodyAndHeader("direct://cataloguing2.update", is,
				"resourceDescriptorId", rd.getResourceDescriptorId());
		mockUpdateRecord.assertIsSatisfied();
		mockUpdateRecord.expectedMessageCount(1);
	}

	@Test
	public void testFinalizeResourceDescriptor() throws InterruptedException,
			FileNotFoundException {

		InputStream is = new FileInputStream("data/xiyouji.json");
		Long id = template.requestBody("direct://cataloguing2.create", is,
				Long.class);

		MockEndpoint mockFinalizeRecord = getMockEndpoint("mock:cataloguing2.finalize");
		is = new FileInputStream("data/xiyouji.json");
		template.sendBodyAndHeader("direct://cataloguing2.finalize", is, "id",
				id);
		mockFinalizeRecord.assertIsSatisfied();
		mockFinalizeRecord.expectedMessageCount(1);
	}

	@Test
	public void testReviseResourceDescriptor() throws InterruptedException,
			FileNotFoundException {

		InputStream is = new FileInputStream("data/xiyouji.json");
		Long id = template.requestBody("direct://cataloguing2.create", is,
				Long.class);

		is = new FileInputStream("data/xiyouji.json");
		template.sendBodyAndHeader("direct://cataloguing2.finalize", is, "id",
				id);

		MockEndpoint mockReviseRecord = getMockEndpoint("mock:cataloguing2.revise");
		is = new FileInputStream("data/xiyouji.json");
		template.sendBodyAndHeader("direct://cataloguing2.revise", is, "id", id);
		mockReviseRecord.assertIsSatisfied();
		mockReviseRecord.expectedMessageCount(1);
	}
	
	@Test
	public void testDeleteResourceDescriptor() throws InterruptedException,
			FileNotFoundException {

		InputStream is = new FileInputStream("data/xiyouji.json");
		Long id = template.requestBody("direct://cataloguing2.create", is,
				Long.class);
		
		is = new FileInputStream("data/xiyouji.json");
		template.sendBodyAndHeader("direct://cataloguing2.finalize", is, "id",
				id);

		MockEndpoint mockDeleteRecord = getMockEndpoint("mock:cataloguing2.delete");
		is = new FileInputStream("data/xiyouji.json");
		template.sendBodyAndHeader("direct://cataloguing2.delete", "", "id", id);
		mockDeleteRecord.assertIsSatisfied();
		mockDeleteRecord.expectedMessageCount(1);
	}

	@Test
	public void testVerify() throws InterruptedException, FileNotFoundException {

		MockEndpoint mockVerifyRecord = getMockEndpoint("mock:cataloguing2.verify");
		InputStream is = new FileInputStream("data/xiyouji.json");
		String response = template.requestBody("direct://cataloguing2.verify",
				is, String.class);
		mockVerifyRecord.assertIsSatisfied();
		mockVerifyRecord.expectedMessageCount(1);
		System.out.println("Response: " + response);
	}

}