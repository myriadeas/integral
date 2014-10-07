package my.com.myriadeas.integral.circulation.service.impl;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
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
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronStatusRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.SMDRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(DEV)
public class RecallServiceTest extends
IntegralAbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RecallServiceImpl recallService;
	@Autowired
	private ReservationTransactionRepositoryImpl ciresvRepo;

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
	private PatronStatusRepositoryImpl glstatRepo;

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

	/*
	@Test
	public void testRecall() {

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
		glicat.setDescription("07 Description");
		glicatRepo.save(glicat);

		Glbrnc glbrnc = new Glbrnc();
		glbrnc.setGl71brnc("HQ");
		glbrnc.setDescription("Head Quarter");
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
		ctmatm.setCt02noresv( 0);
		ctmatm.setCt02status("C");
		ctmatm.setCt02type("C");
		ctmatmRepo.save(ctmatm);

		Glsmd glsmd = new Glsmd();
		glsmd.setGl47smd("BOK");
		glsmd.setDescription("BOK Description");
		glsmdRepo.save(glsmd);

		Gldocs gldocsCirculated = new Gldocs();
		gldocsCirculated.setGl36stat("C");
		gldocsRepo.save(gldocsCirculated);
		Gldocs gldocsRecalled = new Gldocs();
		gldocsRecalled.setGl36stat("E");
		gldocsRepo.save(gldocsRecalled);

		Glcate glcate = new Glcate();
		glcate.setGl07cate("01");
		glcate.setDescription("01 Description");
		glcateRepo.save(glcate);

		Glgrma glgrma = new Glgrma();
		glgrma.setGl02grp("00");
		glgrma.setGl02name("00 Description");
		glgrmaRepo.save(glgrma);

		Glpatr currentBorrower = new Glpatr();
		currentBorrower.setGl14patr("CURRENT_PATR");
		currentBorrower.setGl14brnc(glbrnc);
		currentBorrower.setGl14cate(glcate);
		currentBorrower.setGl14grid(glgrma);
		currentBorrower.setGl14expdate(dateFormatter.parseDateTime(
				"01/01/2020 01:01:01").toDate());
		currentBorrower.setGl14pasw("XX");
		currentBorrower.setGl14name("XX");
		currentBorrower.setGl14memdate(new Date());
		glpatrRepo.save(currentBorrower);

		Ctdocm item = new Ctdocm();
		item.setCt03docno("123");
		item.setCt03icat(glicat);
		item.setCt03loca(glloca);
		item.setCt03cond(glcond);
		item.setCt03matno(ctmatm);
		item.setCt03smd(glsmd);
		item.setCt03stat(gldocsCirculated);
		item.setCt03cirhits(0);

		DateTime ct03bdatetime = dateFormatter
				.parseDateTime("01/01/2020 01:01:01");
		DateTime ct03ddatetime = dateFormatter
				.parseDateTime("11/01/2020 01:01:01");
		item.setCt03bdatetime(ct03bdatetime.toDate());
		item.setCt03ddatetime(ct03ddatetime.toDate());
		item.setCt03patr(currentBorrower);
		item.setCt03cirhits(1);
		ctdocmRepo.save(item);
		long ctdocmId = item.getId();

		Glstat glstat = new Glstat();
		glstat.setGl08stat("01");
		glstatRepo.save(glstat);

		Glpatr reserver1 = new Glpatr();
		reserver1.setGl14patr("PATRON1");
		reserver1.setGl14brnc(glbrnc);
		reserver1.setGl14cate(glcate);
		reserver1.setGl14grid(glgrma);
		reserver1.setGl14expdate(dateFormatter.parseDateTime(
				"01/01/2020 01:01:01").toDate());
		reserver1.setGl14pasw("XX");
		reserver1.setGl14name("XX");
		reserver1.setGl14memdate(new Date());
		glpatrRepo.save(reserver1);

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

		DateTime reserveDateTime = dateFormatter
				.parseDateTime("05/01/2001 01:01:01");
		ReserveResponse reserveResponse = reserveService.reserve(reserver1,
				officer, ctmatm, item, glicat, glbrnc,
				reserveDateTime.toDate());
		assertEquals(1, Integer.parseInt(reserveResponse.getQueuePosition()));
		System.out.println("QueuePosition: "
				+ reserveResponse.getQueuePosition());
		System.out.println("PickupLocation: "
				+ reserveResponse.getPickupLocation());
		List<Ciresv> ciresvList = ciresvRepo.findByCi03docno(item);
		assertEquals(reserver1, ciresvList.get(0).getCi03patr());

		Glpatr reserver2 = new Glpatr();
		reserver2.setGl14patr("PATRON2");
		reserver2.setGl14brnc(glbrnc);
		reserver2.setGl14cate(glcate);
		reserver2.setGl14grid(glgrma);
		reserver2.setGl14expdate(dateFormatter.parseDateTime(
				"01/01/2020 01:01:01").toDate());
		reserver2.setGl14pasw("XX");
		reserver2.setGl14name("XX");
		reserver2.setGl14memdate(new Date());
		glpatrRepo.save(reserver2);

		reserveDateTime = dateFormatter.parseDateTime("05/01/2001 01:01:01");
		reserveResponse = reserveService.reserve(reserver2, officer, ctmatm,
				item, glicat, glbrnc, reserveDateTime.toDate());
		
		
		Glpatr recaller1 = new Glpatr();
		recaller1.setGl14patr("RECALLER1");
		recaller1.setGl14brnc(glbrnc);
		recaller1.setGl14cate(glcate);
		recaller1.setGl14grid(glgrma);
		recaller1.setGl14expdate(dateFormatter.parseDateTime(
				"01/01/2020 01:01:01").toDate());
		recaller1.setGl14pasw("XX");
		recaller1.setGl14name("XX");
		recaller1.setGl14memdate(new Date());
		glpatrRepo.save(recaller1);
		
		Date recallDateTime = dateFormatter.parseDateTime("05/01/2001 01:01:01").toDate();
		RecallResponse recallResponse = recallService.recall(recaller1, item, glbrnc, officer, recallDateTime);
		ciresvList = ciresvRepo.findByCi03docnoOrderByCi03prior(item);
		assertEquals(3, ciresvList.size());
		for (Ciresv ciresv : ciresvList) {
			System.out.println(ciresv.getCi03prior() + " : " + ciresv.getCi03patr() );			
			if (ciresv.getCi03patr().getGl14patr().equals(recaller1.getGl14patr())) {
				assertEquals(1, ciresv.getCi03prior().intValue());
			} else if (ciresv.getCi03patr().getGl14patr().equals(reserver1.getGl14patr())) {
				assertEquals(2, ciresv.getCi03prior().intValue());
			} else if (ciresv.getCi03patr().getGl14patr().equals(reserver2.getGl14patr())) {
				assertEquals(3, ciresv.getCi03prior().intValue());
			} else {
				fail("");
			}			
		}
	}
	*/
	
}
