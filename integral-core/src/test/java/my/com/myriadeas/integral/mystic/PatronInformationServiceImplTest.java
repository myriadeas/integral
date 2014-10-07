package my.com.myriadeas.integral.mystic;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Condition;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;
import my.com.myriadeas.integral.data.jpa.domain.PatronStatus;
import my.com.myriadeas.integral.data.jpa.domain.SMD;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ConditionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.LocationRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronStatusRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.SMDRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PatronInformationServiceImplTest extends
		AbstractTransactionalCirculationTests {
	@Autowired
	private BranchRepositoryImpl glbrncRepo;

	@Autowired
	private PatronCategoryRepositoryImpl glcateRepo;

	@Autowired
	private PatronStatusRepositoryImpl glstatRepo;

	@Autowired
	private PatronRepositoryImpl glpatrRepo;
	
	@Autowired
	private ItemCategoryRepositoryImpl glicatRepo;

	@Autowired
	private ConditionRepositoryImpl glcondRepo;

	@Autowired
	private MaterialRepositoryImpl ctmatmRepo;

	@Autowired
	private LocationRepositoryImpl gllocaRepo;

	@Autowired
	private SMDRepositoryImpl glsmdRepo;


	@Autowired
	private ItemRepositoryImpl ctdocmRepo;

	@Test
	public void testMarker() {

	}

	//@Test
	public void testOnLoanItems() throws SQLException {
		Branch glbrnc = new Branch();
		glbrnc.setCode("HQ");
		glbrnc.setDescription("HQ");
		glbrncRepo.save(glbrnc);

		PatronCategory glcate = new PatronCategory();
		glcate.setCode("01");
		glcate.setDescription("01");
		glcateRepo.save(glcate);

		
		PatronStatus glstat = new PatronStatus();
		glstat.setCode("01");
		glstatRepo.save(glstat);

		Patron patron = new Patron();
		patron.setUsername("PATRON1");
		patron.setBranch(glbrnc);
		patron.setPatronCategory(glcate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
		Date expDate = calendar.getTime();
		patron.setMembershipExpiryDate(expDate);

		patron.setPassword("X");
		patron.setFirstname("XX");
		patron.setMembershipDate(new Date());
		glpatrRepo.save(patron);

		Condition glcond = new Condition();
		glcond.setCode("0");
		glcondRepo.save(glcond);

		ItemCategory glicat = new ItemCategory();
		glicat.setCode("OS1");
		glicat.setDescription("OS1");
		glicatRepo.save(glicat);

		SMD glsmd = new SMD();
		glsmd.setCode("BOK1");
		glsmd.setDescription("BOK1");
		glsmdRepo.save(glsmd);

		Location glloca = new Location();
		glloca.setCode("L11");
		glloca.setBranch(glbrnc);
		gllocaRepo.save(glloca);

		Material ctmatm = getDefaultMaterial();
		ctmatm.setMaterialNo("000000000011");
		ctmatm.setStatus("C");
		ctmatm.setType("C");
		ctmatmRepo.save(ctmatm);

		ItemStatus gldocs = ItemStatus.CIRCULATED;
		Item item = new Item();
		item.setItemIdentifier("00000001");
		item.setPatron(patron);
		item.setCondition(glcond);
		item.setItemCategory(glicat);
		item.setLocation(glloca);
		item.setMaterial(ctmatm);
		item.setSmd(glsmd);
		item.setItemStatus(gldocs);
		ctdocmRepo.save(item);
		System.out.println(item.getId());
		// System.out.println(ctdocmRepo.findOne(gldocsId).getCt03stat());

		List<Item> onLoanItems = patron.getOnLoanItemsFilterByItemCategoriesAndSmdsAndLocations(null, null, null);
		//assertEquals(1, onLoanItems.size());

	}

}
