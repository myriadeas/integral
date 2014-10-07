package my.com.myriadeas.integral.cataloguing;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.enumeration.CataloguingStatusEnum;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.marc4j.MarcJsonWriter;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class CataloguingRouteTest extends CamelTestSupport {

	@Autowired
	@Qualifier("cataloguingServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:welcome", context = "cataloguingServiceRoute")
	protected ProducerTemplate template;

	@EndpointInject(uri = "mock:cataloguing", context = "cataloguingServiceRoute")
	protected MockEndpoint mockEndpoint;

	@Autowired
	private MaterialRepositoryImpl materialRepository;

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testMarker() throws Exception {
	}

	@Test
	public void testAddMaterial() throws InterruptedException {

		MockEndpoint mockAddRecord = getMockEndpoint("mock:cataloguing.create");
		InputStream is;

		try {
			is = new FileInputStream("data/xiyouji.json");
			Response response = template.requestBody("direct://cataloguing.create",
					is, Response.class);
			mockAddRecord.assertIsSatisfied();
			mockAddRecord.expectedMessageCount(1);
			is.close();
			System.out.println(response);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testAddMaterial_String_UTF8() throws InterruptedException {

		MockEndpoint mockAddRecord = getMockEndpoint("mock:cataloguing.create");
		InputStream is;

		String marcJson = "{\"leader\":\"01471cjm a2200349 a 4500\""
				+ ",\"fields\":[{\"245\":{\"subfields\":[{\"a\":\"李白与杜甫\"}],\"ind1\":\"0\",\"ind2\":\"0\"}}]}";

		String response = template.requestBody("direct://cataloguing.create", marcJson,
				String.class);
		mockAddRecord.assertIsSatisfied();
		mockAddRecord.expectedMessageCount(1);
		System.out.println(response);

	}

	public void testUpdateAndDeleteMaterial() {
		String marc = "00058nam  2200037Ia 450024500200000000aTime Management";

		Material material = new Material.MaterialBuilder(marc).materialNo(
				"0000000099").build();
		material = materialRepository.save(material);

		// create a factory instance
		MarcFactory factory = MarcFactory.newInstance();

		// create a record with leader
		Record record = factory.newRecord("00000cam a2200000 a 4500");

		// add a control field
		record.addVariableField(factory.newControlField("001", "12883376"));

		// add a data field
		DataField df = factory.newDataField("245", '1', '0');
		df.addSubfield(factory.newSubfield('a', "Summerland/"));
		df.addSubfield(factory.newSubfield('c', "Michael Chabon."));
		record.addVariableField(df);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MarcJsonWriter jsonWriter = new MarcJsonWriter(baos);
		jsonWriter.write(record);
		System.out.println(baos);

		// update
		template.requestBodyAndHeader("direct://cataloguing.update", baos,
				"marcid", material.getMaterialNo());

		String expectedMarc = "00119cam a2200061 a 4500001001100000245003300011010001300044000000001110aSummerland/cMichael Chabon.  a12883376";

		material = materialRepository
				.findByMaterialNo(material.getMaterialNo());
		assertEquals(expectedMarc, material.getMarc());

		// delete
		template.requestBodyAndHeader("direct://cataloguing.delete", "", "marcid",
				material.getMaterialNo());

		material = materialRepository
				.findByMaterialNo(material.getMaterialNo());
		assertFalse(material.getStatus().equals(CataloguingStatusEnum.INDEXED));

	}

}
