package my.com.myriadeas.integral.mystic;

import java.io.IOException;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.cataloguing.facade.impl.MarcServiceFacadeImpl;
import my.com.myriadeas.integral.circulation.command.CheckOutEventCommand;
import my.com.myriadeas.integral.circulation.command.ReleaseEventCommand;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.circulation.exception.ItemStatusNotAllowedForReleaseException;
import my.com.myriadeas.integral.circulation.request.ReleaseRequest;
import my.com.myriadeas.integral.circulation.response.ReleaseResponse;
import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractCamelTestSupportTests;
import my.com.myriadeas.integral.core.ItemNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.populator.BranchData;
import my.com.myriadeas.integral.data.populator.ConditionData;
import my.com.myriadeas.integral.data.populator.ItemCategoryData;
import my.com.myriadeas.integral.data.populator.LocationData;
import my.com.myriadeas.integral.data.populator.MaterialData;
import my.com.myriadeas.integral.data.populator.OfficerData;
import my.com.myriadeas.integral.data.populator.PatronCategoryData;
import my.com.myriadeas.integral.data.populator.PatronItemEligibilityData;
import my.com.myriadeas.integral.data.populator.SmdData;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Before;
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
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class ReleaseRouteTest extends IntegralAbstractCamelTestSupportTests 
	implements BranchData, PatronCategoryData, ItemCategoryData, LocationData,
	SmdData, MaterialData, ConditionData, PatronItemEligibilityData,
	OfficerData{

	@Autowired
	@Qualifier("circulationServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:start2", context = "circulationServiceRoute")
	protected ProducerTemplate template;
	
		
	@Autowired
	private MarcServiceFacadeImpl marcService;
	
	@Autowired
	private MaterialRepositoryImpl materialRepo;
	
	@Autowired
	private ItemRepositoryImpl itemRepo;

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testMarker() throws Exception {
	}

	//@Test
	public void testRelease() throws InterruptedException, RuntimeException, IOException{
		
		Record record = getRecordsFromFile("marcRecords.mrc").get(0);
		record = marcService.create("", "", record);
		System.out
				.println("getControlNumber=" + record.getControlNumber());
		Material material = materialRepo.findByMaterialNo(String
				.valueOf(record.getControlNumber()));

		String itemIdentifier = "REL0001";
		Item item = new Item(itemIdentifier, OPEN_SHELF,
				LEVEL_ONE_MAIN_BRANCH, material, BOOK, ItemStatus.FINAL_PROCESSING,
				GOOD_CONDITION);
		itemRepo.save(item);
		
		assertEquals(ItemStatus.FINAL_PROCESSING, itemRepo.findById(item.getId()).getItemStatus());
		
		ReleaseRequest request = new ReleaseRequest();
		request.setItemIdentifier(itemIdentifier);
		ReleaseResponse response = template.requestBody("direct://circulation.release", request, ReleaseResponse.class);		
		item = itemRepo.findById(item.getId());
		assertEquals(ItemStatus._RETURNED, item.getItemStatus());
		
		ReleaseEventCommand releaseEventCommand = new ReleaseEventCommand(item);		
		template.requestBody("direct:event.release", releaseEventCommand);
		item = itemRepo.findById(item.getId());
		assertEquals(ItemStatus.AVAILABLE, item.getItemStatus());
		
	}
	
	
	//@Test
	public void testReleaseFail() throws InterruptedException, RuntimeException, IOException{
		String itemIdentifier = "REL0001";
		ReleaseRequest request = new ReleaseRequest();
		request.setItemIdentifier(itemIdentifier);
		try {
			ReleaseResponse response = template.requestBody("direct://circulation.release", request, ReleaseResponse.class);
			fail("Should throw exception");
		} catch (Exception e){
			assertTrue(e.getCause().getCause() instanceof ItemNotFoundException);
			if (e.getCause().getCause() instanceof ItemNotFoundException){
				System.out.println(e.getCause().getCause().toString());				
			}			
		}
		
		
		Record record = getRecordsFromFile("marcRecords.mrc").get(0);
		record = marcService.create("", "", record);
		System.out
				.println("getControlNumber=" + record.getControlNumber());
		Material material = materialRepo.findByMaterialNo(String
				.valueOf(record.getControlNumber()));

		itemIdentifier = "REL0001";
		Item item = new Item(itemIdentifier, OPEN_SHELF,
				LEVEL_ONE_MAIN_BRANCH, material, BOOK, ItemStatus.AVAILABLE,
				GOOD_CONDITION);
		itemRepo.save(item);
		
		assertEquals(ItemStatus.AVAILABLE, itemRepo.findById(item.getId()).getItemStatus());
		
		request = new ReleaseRequest();
		request.setItemIdentifier(itemIdentifier);
		try {
			ReleaseResponse response = template.requestBody("direct://circulation.release", request, ReleaseResponse.class);
			fail("Should throw exception");
		} catch (Exception e){
			assertTrue(e.getCause().getCause() instanceof ItemStatusNotAllowedForReleaseException);
			if (e.getCause().getCause() instanceof ItemStatusNotAllowedForReleaseException){
				System.out.println(e.getCause().getCause().toString());				
			}			
		}
		
		
	}
	
	
	
}
