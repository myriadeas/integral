package my.com.myriadeas.integral.mystic;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckInRouteTest extends IntegralAbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	@Qualifier("circulationServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:start2", context = "circulationServiceRoute")
	protected ProducerTemplate template;

	/*
	@Autowired
	private GlpatrRepository glpatrRepo;

	@Autowired
	private CtdocmRepository ctdocmRepo;
	
	@Autowired
	private GlbrncRepository glbrncRepo;

	@Autowired
	private GlsmdRepository glsmdRepo;

	@Autowired
	private GldocsRepository gldocsRepo;

	@Autowired
	private GlcateRepository glcateRepo;

	@Autowired
	private GlgrmaRepository glgrmaRepo;

	@Autowired
	private GlstatRepository glstatRepo;
	
	@Autowired
	private GlicatRepository glicatRepo;

	@Autowired
	private GlcondRepository glcondRepo;

	@Autowired
	private CtmatmRepository ctmatmRepo;

	@Autowired
	private GllocaRepository gllocaRepo;

	@Autowired
	private CicircRepository cicircRepo;

	*/
	
	@EndpointInject(uri = "mock:returnHandler", context = "circulationServiceRoute")
	protected MockEndpoint mockReturnHandler;

	@Test
	public void testMarker() throws Exception {
	}

	/*	
	@Test
	//Scenario1: Return items successfully
	public void testScenario1() {
		//Given
		String itemIdentifier = "0000000674";
		String patronIdentifier = "PATRON";
		Date dueDateTime = getDate("21/01/2014");
		Date checkInDateTime = getDate("21/01/2014");		
		String itemStatus = "C";
		String cicircFlag = CirculationTransactionFlag.CHARGED;
		
		//Ctdocm item = ctdocmDataFactory.createCtdocmWithStatusCirculated(itemIdentifier, patronIdentifier, checkInDateTime, dueDateTime);
		//Glpatr patron = glpatrDataFactory.createGlpatr("PATRON");		
		Cicirc cicirc = cicircDataFactory.createChargedCicirc(itemIdentifier, patronIdentifier, checkInDateTime, dueDateTime);
				
		CheckInRequest request = new CheckInRequest();
		try {
			
			request.setItemIdentifier(itemIdentifier);
			request.setTransactionDate(checkInDateTime);
			CheckInResponse checkInResponse = template.requestBody("direct://checkin", request,
					CheckInResponse.class);
			
			assertEquals(itemStateService.get_RETURNED().getGl36stat(), checkInResponse.getItem().getCt03stat().getGl36stat());
			assertEquals(checkInDateTime, checkInResponse.getCirculationDetail().getCi02didatetime());
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			fail("Should not throw exception");
		}
		
	}
	*/
	
	/*
	@Test
	//Scenario2: Patron wants to check out an item but go to check in counter
	public void testScenario2() {
		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy");
		
		String itemIdentifier = "0000000676";
		DateTime dueDateTime = dateFormatter
				.parseDateTime("21/01/2014");
		DateTime checkInDateTime = dateFormatter
				.parseDateTime("21/01/2014");
		Gldocs itemStatus = itemStateService.getAVAILABLE();
		
		Glicat glicat = new Glicat();
		glicat.setGl10icat("07");
		glicat.setDescription("07");
		glicatRepo.save(glicat);

		Glbrnc glbrnc = new Glbrnc();
		glbrnc.setGl71brnc("HQ");
		glbrnc.setDescription("HQ");
		glbrncRepo.save(glbrnc);

		Glloca glloca = new Glloca();
		glloca.setGl05loca("L1");
		glloca.setGl05brnc(glbrnc);
		gllocaRepo.save(glloca);

		Glcond glcond = new Glcond();
		glcond.setGl06cond("00");
		glcondRepo.save(glcond);

		Ctmatm ctmatm = new Ctmatm();
		ctmatm.setCt02matno("000001");
		ctmatm.setCt02status("C");
		ctmatm.setCt02type("C");
		ctmatmRepo.save(ctmatm);

		Glsmd glsmd = new Glsmd();
		glsmd.setGl47smd("BOK");
		glsmd.setDescription("BOK");
		glsmdRepo.save(glsmd);

		Ctdocm item = new Ctdocm();
		item.setCt03docno(itemIdentifier);
		item.setCt03icat(glicat);
		item.setCt03loca(glloca);
		item.setCt03cond(glcond);
		item.setCt03matno(ctmatm);
		item.setCt03smd(glsmd);
		item.setCt03stat(itemStatus);
		item.setCt03cirhits(0);
		ctdocmRepo.save(item);
				
		Glcate glcate = new Glcate();
		glcate.setGl07cate("01");
		glcate.setDescription("01");
		glcateRepo.save(glcate);

		Glgrma glgrma = new Glgrma();
		glgrma.setGl02grp("00");
		glgrmaRepo.save(glgrma);

		Glstat glstat = new Glstat();
		glstat.setGl08stat("01");
		glstatRepo.save(glstat);

		Glpatr patron = new Glpatr();
		patron.setGl14patr("PATRON1");
		patron.setGl14brnc(glbrnc);
		patron.setGl14cate(glcate);
		patron.setGl14grid(glgrma);
		patron.setGl14expdate(dateFormatter
				.parseDateTime("01/01/2020").toDate());
		patron.setGl14pasw("XX");
		patron.setGl14name("XX");
		patron.setGl14memdate(new Date());
		glpatrRepo.save(patron);

		Glpatr officer = new Glpatr();
		officer.setGl14patr("OFFICER1");
		officer.setGl14brnc(glbrnc);
		officer.setGl14cate(glcate);
		officer.setGl14grid(glgrma);
		officer.setGl14expdate(dateFormatter.parseDateTime(
				"01/01/2020").toDate());
		officer.setGl14pasw("XX");
		officer.setGl14name("XX");
		officer.setGl14memdate(new Date());
		glpatrRepo.save(officer);

		
		CheckInRequest request = new CheckInRequest();
		try {
			request.setOfficerIdentifier(officer.getGl14patr());
			request.setItemIdentifier(itemIdentifier);
			CheckInResponse checkInResponse = template.requestBody("direct://checkin", request,
					CheckInResponse.class);
			
			fail("Should throw exception");
			
		} catch (InvalidAccessionStatusException e) {
			
		} catch (CamelExecutionException cx){
			
		}
		
	}

	*/
	
	
}
