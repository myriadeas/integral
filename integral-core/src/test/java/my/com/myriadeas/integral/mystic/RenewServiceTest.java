package my.com.myriadeas.integral.mystic;

import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;
import my.com.myriadeas.integral.data.jpa.repositories.PatronStatusRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ConditionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.LocationRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronEligibilityRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.SMDRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RenewServiceTest extends
		IntegralAbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PatronItemEligibilityRepositoryImpl glpatritemeligRepo;

	@Autowired
	private PatronEligibilityRepositoryImpl glpatreligRepo;

	@Autowired
	private BranchRepositoryImpl glbrncRepo;

	@Autowired
	private PatronCategoryRepositoryImpl glcateRepo;

	
	@Autowired
	private PatronRepositoryImpl glpatrRepo;

	@Autowired
	private ItemCategoryRepositoryImpl glicatRepo;

	@Autowired
	private ConditionRepositoryImpl glcondRepo;

	@Autowired
	private PatronStatusRepository glstatRepo;

	@Autowired
	private MaterialRepositoryImpl ctmatmRepo;

	@Autowired
	private LocationRepositoryImpl gllocaRepo;

	@Autowired
	private ItemRepositoryImpl ctdocmRepo;

	@Autowired
	private SMDRepositoryImpl glsmdRepo;

	@Autowired
	private CirculationTransactionRepositoryImpl cicircRepo;

	@Test
	public void test() {

	}

	//@Test
	/*-
	public void testRenewNoOverdue() {

		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy HH:mm:ss");

		List<Glfine> glfineList = new ArrayList<Glfine>();
		Glfine glfine1to2 = new Glfine();
		glfine1to2.setGl38start(1);
		glfine1to2.setGl38stop(2);
		glfine1to2.setGl38rate(new BigDecimal(0.1));
		glfine1to2.setGl38first(new BigDecimal(0));

		Glfine glfine3to4 = new Glfine();
		glfine3to4.setGl38start(3);
		glfine3to4.setGl38stop(4);
		glfine3to4.setGl38rate(new BigDecimal(0.2));
		glfine3to4.setGl38first(new BigDecimal(0.2));

		Glfine glfine5above = new Glfine();
		glfine5above.setGl38start(5);
		glfine5above.setGl38stop(null);
		glfine5above.setGl38rate(new BigDecimal(0.3));
		glfine5above.setGl38first(new BigDecimal(0.6));

		// Daily Loan
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

		Gldocs gldocs = new Gldocs();
		gldocs.setGl36stat("A");
		gldocsRepo.save(gldocs);

		Ctdocm item = new Ctdocm();
		item.setCt03docno("123");
		item.setCt03icat(glicat);
		item.setCt03loca(glloca);
		item.setCt03cond(glcond);
		item.setCt03matno(ctmatm);
		item.setCt03smd(glsmd);
		item.setCt03stat(gldocs);
		item.setCt03cirhits(0);
		ctdocmRepo.save(item);
		long ctdocmId = item.getId();

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
				.parseDateTime("01/01/2020 01:01:01").toDate());
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
				"01/01/2020 01:01:01").toDate());
		officer.setGl14pasw("XX");
		officer.setGl14name("XX");
		officer.setGl14memdate(new Date());
		glpatrRepo.save(officer);

		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27elig(10);
		glpatritemelig.setGl27ploan(10);
		glpatritemelig.setGl27ptype("D");
		glpatritemelig.setGl27patritemelig("patritemelig");
		glpatritemelig.setGl27maxfine(new BigDecimal(50));
		glpatritemeligRepo.save(glpatritemelig);

		Glpatrelig glpatrelig = new Glpatrelig();
		glpatrelig.setGl77allowovd(true);
		glpatrelig.setGl77elig(10);
		glpatrelig.setGl77patrelig("patrelig");
		glpatrelig.setGl77maxfine(new BigDecimal(50));
		glpatrelig.setDescription("patrelig");
		glpatreligRepo.save(glpatrelig);

		DateTime checkOutDateTime = dateFormatter
				.parseDateTime("01/01/2001 01:01:01");
		DateTime renewDateTime = dateFormatter
				.parseDateTime("11/01/2001 01:01:01");
		checkOutService.checkOut(item, patron, officer,
				checkOutDateTime.toDate());
		CheckOutRequest request = new CheckOutRequest();
		request.setItemIdentifier("123");
		request.setPatronIdentifier("PATRON1");
		CheckOutResponse checkOutResponse = checkOutService
				.constructResponse(request);
		System.out.println(checkOutResponse.getDueDate());
		Cicirc cicirc = cicircRepo.findByCi02patrCi02matnoCi02docnoCi02flag(
				patron, ctmatm, item, CirculationTransactionFlag.CHARGED).get(0);
		renewService.renew(item, cicirc, patron, officer,
				checkOutResponse.getDueDate(), renewDateTime.toDate());
		RenewRequest renewRequest = new RenewRequest();
		renewRequest.setItemIdentifier("123");
		RenewResponse renewResponse = renewService
				.constructResponse(renewRequest);
		// assertNull(renewResponse.getFeeAmount());
		assertEquals(CirculationTransactionFlag.DISCHARGED, cicirc.getCi02flag());
		assertEquals(CirculationTransactionFlag.CHARGED, renewResponse.getCirculationDetail()
				.getCi02flag());
		assertEquals(cicirc.getId(), renewResponse.getCirculationDetail()
				.getCi02renewCircParent().getId());
		assertEquals(new Integer(1), renewResponse.getCirculationDetail()
				.getCi02renew());
		System.out.println(renewResponse.getDueDate());
		System.out.println(renewResponse.getFeeAmount());

	}

	/*-
	@Test
	public void testRenewWithOverdue() {
		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy HH:mm:ss");

		List<Glfine> glfineList = new ArrayList<Glfine>();
		Glfine glfine1to2 = new Glfine();
		glfine1to2.setGl38start(1);
		glfine1to2.setGl38stop(2);
		glfine1to2.setGl38rate(new BigDecimal(0.1));
		glfine1to2.setGl38first(new BigDecimal(0));

		Glfine glfine3to4 = new Glfine();
		glfine3to4.setGl38start(3);
		glfine3to4.setGl38stop(4);
		glfine3to4.setGl38rate(new BigDecimal(0.2));
		glfine3to4.setGl38first(new BigDecimal(0.2));

		Glfine glfine5above = new Glfine();
		glfine5above.setGl38start(5);
		glfine5above.setGl38stop(null);
		glfine5above.setGl38rate(new BigDecimal(0.3));
		glfine5above.setGl38first(new BigDecimal(0.6));

		// Daily Loan
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

		Gldocs gldocs = new Gldocs();
		gldocs.setGl36stat("A");
		gldocsRepo.save(gldocs);

		Ctdocm item = new Ctdocm();
		item.setCt03docno("123");
		item.setCt03icat(glicat);
		item.setCt03loca(glloca);
		item.setCt03cond(glcond);
		item.setCt03matno(ctmatm);
		item.setCt03smd(glsmd);
		item.setCt03stat(gldocs);
		item.setCt03cirhits(0);
		ctdocmRepo.save(item);
		long ctdocmId = item.getId();

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
				.parseDateTime("01/01/2020 01:01:01").toDate());
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
				"01/01/2020 01:01:01").toDate());
		officer.setGl14pasw("XX");
		officer.setGl14name("XX");
		officer.setGl14memdate(new Date());
		glpatrRepo.save(officer);

		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27elig(10);
		glpatritemelig.setGl27ploan(10);
		glpatritemelig.setGl27ptype("D");
		glpatritemelig.setGl27patritemelig("patritemelig");
		glpatritemelig.setGl27maxfine(new BigDecimal(50));
		glpatritemeligRepo.save(glpatritemelig);

		Glpatrelig glpatrelig = new Glpatrelig();
		glpatrelig.setGl77allowovd(true);
		glpatrelig.setGl77elig(10);
		glpatrelig.setGl77patrelig("patrelig");
		glpatrelig.setGl77maxfine(new BigDecimal(50));
		glpatrelig.setDescription("patrelig");
		glpatreligRepo.save(glpatrelig);

		DateTime checkOutDateTime = dateFormatter
				.parseDateTime("01/01/2001 01:01:01");
		DateTime renewDateTime = dateFormatter
				.parseDateTime("14/01/2001 01:01:01");
		CheckOutResponse checkOutResponse = checkOutService.checkOut(item,
				patron, officer, glpatritemelig, checkOutDateTime.toDate());
		System.out.println(checkOutResponse.getDueDate());
		Cicirc cicirc = cicircRepo.findByCi02patrCi02matnoCi02docnoCi02flag(
				patron, ctmatm, item, "C").get(0);
		glfineList.clear();
		glfineList.add(glfine1to2);
		RenewResponse renewResponse = renewService.renew(item, cicirc,
				patron, officer, glpatritemelig, glpatrelig, glfineList,
				checkOutResponse.getDueDate(), renewDateTime.toDate());
		// assertNotNull(renewResponse.getFeeAmount());
		assertEquals(DISCHARGED, cicirc.getCi02flag());
		assertEquals(CHARGED, renewResponse.getCirculationDetail()
				.getCi02flag());
		assertEquals(cicirc.getId(), renewResponse.getCirculationDetail()
				.getCi02renewCircParent().getId());
		assertEquals(new Integer(1), renewResponse.getCirculationDetail()
				.getCi02renew());
		System.out.println(renewResponse.getDueDate());
		System.out.println(renewResponse.getFeeAmount());

	}

	@Test
	public void testSecondRenewal() {

		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy HH:mm:ss");

		List<Glfine> glfineList = new ArrayList<Glfine>();
		Glfine glfine1to2 = new Glfine();
		glfine1to2.setGl38start(1);
		glfine1to2.setGl38stop(2);
		glfine1to2.setGl38rate(new BigDecimal(0.1));
		glfine1to2.setGl38first(new BigDecimal(0));

		Glfine glfine3to4 = new Glfine();
		glfine3to4.setGl38start(3);
		glfine3to4.setGl38stop(4);
		glfine3to4.setGl38rate(new BigDecimal(0.2));
		glfine3to4.setGl38first(new BigDecimal(0.2));

		Glfine glfine5above = new Glfine();
		glfine5above.setGl38start(5);
		glfine5above.setGl38stop(null);
		glfine5above.setGl38rate(new BigDecimal(0.3));
		glfine5above.setGl38first(new BigDecimal(0.6));

		// Daily Loan
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

		Gldocs gldocs = new Gldocs();
		gldocs.setGl36stat("A");
		gldocsRepo.save(gldocs);

		Ctdocm item = new Ctdocm();
		item.setCt03docno("123");
		item.setCt03icat(glicat);
		item.setCt03loca(glloca);
		item.setCt03cond(glcond);
		item.setCt03matno(ctmatm);
		item.setCt03smd(glsmd);
		item.setCt03stat(gldocs);
		item.setCt03cirhits(0);
		ctdocmRepo.save(item);
		long ctdocmId = item.getId();

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
				.parseDateTime("01/01/2020 01:01:01").toDate());
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
				"01/01/2020 01:01:01").toDate());
		officer.setGl14pasw("XX");
		officer.setGl14name("XX");
		officer.setGl14memdate(new Date());
		glpatrRepo.save(officer);

		Glpatritemelig glpatritemelig = new Glpatritemelig();
		glpatritemelig.setGl27elig(10);
		glpatritemelig.setGl27ploan(10);
		glpatritemelig.setGl27ptype("D");
		glpatritemelig.setGl27patritemelig("patritemelig");
		glpatritemelig.setGl27maxfine(new BigDecimal(50));
		glpatritemeligRepo.save(glpatritemelig);

		Glpatrelig glpatrelig = new Glpatrelig();
		glpatrelig.setGl77allowovd(true);
		glpatrelig.setGl77elig(10);
		glpatrelig.setGl77patrelig("patrelig");
		glpatrelig.setGl77maxfine(new BigDecimal(50));
		glpatrelig.setDescription("patrelig");
		glpatreligRepo.save(glpatrelig);

		DateTime checkOutDateTime = dateFormatter
				.parseDateTime("01/01/2001 01:01:01");
		DateTime renewDateTime = dateFormatter
				.parseDateTime("11/01/2001 01:01:01");
		CheckOutResponse checkOutResponse = checkOutService.checkOut(item,
				patron, officer, glpatritemelig, checkOutDateTime.toDate());
		System.out.println(checkOutResponse.getDueDate());
		Cicirc cicirc = cicircRepo.findByCi02patrCi02matnoCi02docnoCi02flag(
				patron, ctmatm, item, CHARGED).get(0);
		RenewResponse renewResponse = renewService.renew(item, cicirc,
				patron, officer, glpatritemelig, glpatrelig, glfineList,
				checkOutResponse.getDueDate(), renewDateTime.toDate());

		renewDateTime = dateFormatter.parseDateTime("12/01/2001 01:01:01");
		cicirc = cicircRepo.findByCi02patrCi02matnoCi02docnoCi02flag(patron,
				ctmatm, item, CHARGED).get(0);
		renewResponse = renewService.renew(item, cicirc, patron, officer,
				glpatritemelig, glpatrelig, glfineList,
				checkOutResponse.getDueDate(), renewDateTime.toDate());

		assertEquals(DISCHARGED, cicirc.getCi02flag());
		assertEquals(CHARGED, renewResponse.getCirculationDetail()
				.getCi02flag());
		assertEquals(cicirc.getId(), renewResponse.getCirculationDetail()
				.getCi02renewCircParent().getId());
		assertEquals(new Integer(2), renewResponse.getCirculationDetail()
				.getCi02renew());

		System.out.println(renewResponse.getDueDate());
		System.out.println(renewResponse.getFeeAmount());

	}
	 */
}
