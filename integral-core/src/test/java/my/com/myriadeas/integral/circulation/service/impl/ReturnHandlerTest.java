package my.com.myriadeas.integral.circulation.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import my.com.myriadeas.integral.circulation.handler.impl.ReserveHandlerImpl;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Condition;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;
import my.com.myriadeas.integral.data.jpa.domain.SMD;
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
import my.com.myriadeas.integral.data.jpa.repositories.impl.UserRepositoryImpl;
import my.com.myriadeas.integral.mystic.AbstractTransactionalCirculationTests;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReturnHandlerTest extends
AbstractTransactionalCirculationTests{

	@Autowired
	private ReserveHandlerImpl returnHandler;

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
	private UserRepositoryImpl userRepo;


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

	@Test
	public void testNoReserver() {
		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy");

		ItemCategory glicat = new ItemCategory();
		glicat.setCode("07");
		glicat.setDescription("07");
		glicatRepo.save(glicat);

		Branch glbrnc = new Branch();
		glbrnc.setCode("HQ");
		glbrnc.setDescription("Head Quarter");
		glbrncRepo.save(glbrnc);

		Location glloca = new Location();
		glloca.setCode("L1");
		glloca.setBranch(glbrnc);
		gllocaRepo.save(glloca);

		Condition glcond = new Condition();
		glcond.setCode("00");
		glcondRepo.save(glcond);

		Material ctmatm = getDefaultMaterial();
		ctmatm.setMaterialNo("000001");
		ctmatm.setStatus("C");
		ctmatm.setType("C");
		ctmatmRepo.save(ctmatm);

		SMD glsmd = new SMD();
		glsmd.setCode("BOK");
		glsmd.setDescription("BOK");
		glsmdRepo.save(glsmd);

		PatronCategory glcate = new PatronCategory();
		glcate.setCode("01");
		glcate.setDescription("01");
		glcateRepo.save(glcate);

		
		Patron currentBorrower = new Patron();
		currentBorrower.setUsername("CURRENT_PATR");
		currentBorrower.setBranch(glbrnc);
		currentBorrower.setPatronCategory(glcate);
		currentBorrower.setMembershipExpiryDate(dateFormatter.parseDateTime(
				"01/01/2020").toDate());
		currentBorrower.setPassword("XX");
		currentBorrower.setFirstname("XX");
		currentBorrower.setMembershipDate(new Date());
		glpatrRepo.save(currentBorrower);

		Item item = new Item();
		item.setItemIdentifier("123");
		item.setItemCategory(glicat);
		item.setLocation(glloca);
		item.setCondition(glcond);
		item.setMaterial(ctmatm);
		item.setSmd(glsmd);
		item.setItemStatus(ItemStatus._RETURNED);
		
		DateTime ct03bdatetime = dateFormatter.parseDateTime("01/01/2020");
		DateTime ct03ddatetime = dateFormatter.parseDateTime("11/01/2020");
		item.setChargeDateTime(ct03bdatetime.toDate());
		item.setDueDateTime(ct03ddatetime.toDate());
		item.setPatron(currentBorrower);
		ctdocmRepo.save(item);

		// no reserver
		DateTime checkInDateTime = dateFormatter.parseDateTime("01/01/2020");
		returnHandler.handleReserve(item, checkInDateTime.toDate());
		Item resultCtdocm = ctdocmRepo.findById(item.getId());
		assertEquals(ItemStatus.AVAILABLE.getCode(), resultCtdocm
				.getItemStatus().getCode());
		assertNull(resultCtdocm.getPatron());
		assertNull(resultCtdocm.getChargeDateTime());
		assertNull(resultCtdocm.getDueDateTime());

	}

	/*
	@Test
	public void testHasReserver() {
		DateTimeFormatter dateFormatter = DateTimeFormat
				.forPattern("dd/MM/yyyy");

		ItemCategory glicat = new ItemCategory();
		glicat.setCode("07");
		glicat.setDescription("07");
		glicatRepo.save(glicat);

		Branch glbrnc = new Branch();
		glbrnc.setCode("HQ");
		glbrnc.setDescription("Head Quarter");
		glbrncRepo.save(glbrnc);

		Location glloca = new Location();
		glloca.setCode("L1");
		glloca.setBranch(glbrnc);
		gllocaRepo.save(glloca);

		Condition glcond = new Condition();
		glcond.setCode("00");
		glcondRepo.save(glcond);

		Material ctmatm = getDefaultMaterial();
		ctmatm.setMaterialNo("000001");
		ctmatm.setStatus("C");
		ctmatm.setType("C");
		ctmatmRepo.save(ctmatm);

		SMD glsmd = new SMD();
		glsmd.setCode("BOK");
		glsmd.setDescription("BOK");
		glsmdRepo.save(glsmd);

		PatronCategory glcate = new PatronCategory();
		glcate.setCode("01");
		glcate.setDescription("01");
		glcateRepo.save(glcate);

		Patron currentBorrower = new Patron();
		currentBorrower.setUsername("CURRENT_PATR");
		currentBorrower.setBranch(glbrnc);
		currentBorrower.setPatronCategory(glcate);
		currentBorrower.setMembershipExpiryDate(dateFormatter.parseDateTime(
				"01/01/2020").toDate());
		currentBorrower.setPassword("XX");
		currentBorrower.setFirstname("XX");
		currentBorrower.setMembershipDate(new Date());
		glpatrRepo.save(currentBorrower);

		Item item = new Item();
		item.setItemIdentifier("123");
		item.setItemCategory(glicat);
		item.setLocation(glloca);
		item.setCondition(glcond);
		item.setMaterial(ctmatm);
		item.setSmd(glsmd);
		item.setStatus(ItemStateServiceImpl._RETURNED);
		
		DateTime ct03bdatetime = dateFormatter.parseDateTime("01/01/2020");
		DateTime ct03ddatetime = dateFormatter.parseDateTime("11/01/2020");
		item.setChargeDateTime(ct03bdatetime.toDate());
		item.setDueDateTime(ct03ddatetime.toDate());
		item.setPatron(currentBorrower);
		ctdocmRepo.save(item);

		Patron reserver1 = new Patron();
		reserver1.setUsername("PATRON1");
		reserver1.setBranch(glbrnc);
		reserver1.setPatronCategory(glcate);
		reserver1.setMembershipExpiryDate(dateFormatter.parseDateTime(
				"01/01/2020").toDate());
		reserver1.setPassword("XX");
		reserver1.setFirstname("XX");
		reserver1.setMembershipDate(new Date());
		glpatrRepo.save(reserver1);

		Officer officer = new Officer();
		officer.setUsername("OFFICER1");
		officer.setBranch(glbrnc);
		officer.setPatronCategory(glcate);
		officer.setMembershipExpiryDate(dateFormatter.parseDateTime(
				"01/01/2020").toDate());
		officer.setPassword("XX");
		officer.setFirstname("XX");
		officer.setMembershipDate(new Date());
		userRepo.save(officer);

		// has reserver
		ReservationTransaction ciresv = new ReservationTransaction();
		ciresv.setPatron(reserver1);
		ciresv.setStatus(ReservationStatus.RESERVE);
		ciresv.setMaterial(ctmatm);
		ciresv.setBranch(glbrnc);
		ciresv.setReserveDateTime(dateFormatter.parseDateTime("05/01/2001")
				.toDate());
		ciresv.setPriorityWeight((Integer) 1);
		ciresv.setOfficer(officer);
		ciresv.setItem(item);
		ciresv.setItemCategory(glicat);
		ciresvRepo.save(ciresv);

		DateTime checkInDateTime = dateFormatter.parseDateTime("01/01/2020");
		returnHandler.handleReserve(item, checkInDateTime.toDate());
		Item resultCtdocm = ctdocmRepo.findById(item.getId());
		assertEquals(ItemStateServiceImpl.HOLD.getCode(), resultCtdocm
				.getStatus().getCode());
		assertNull(resultCtdocm.getPatron());
		assertNull(resultCtdocm.getChargeDateTime());
		assertNull(resultCtdocm.getDueDateTime());

	}
	*/
	
}
