package my.com.myriadeas.integral.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.cataloguing.facade.impl.MarcServiceFacadeImpl;
import my.com.myriadeas.integral.data.jpa.domain.Fine;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.OfficerRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronItemEligibilityRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.populator.BranchData;
import my.com.myriadeas.integral.data.populator.ItemData;
import my.com.myriadeas.integral.data.populator.ItemDatabasePopulator;
import my.com.myriadeas.integral.data.populator.OfficerDatabasePopulator;
import my.com.myriadeas.integral.data.populator.PatronCategoryData;
import my.com.myriadeas.integral.data.populator.PatronDatabasePopulator;
import my.com.myriadeas.integral.data.populator.PatronItemEligibilityDatabasePopulator;
import my.com.myriadeas.integral.data.populator.ReceiptingTransactionCodeDatabasePopulator;
import my.com.myriadeas.integral.data.populator.RoleData;
import my.com.myriadeas.integral.eligibility.Eligibility;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;
import my.com.myriadeas.integral.security.jpa.SecurityService;

import org.apache.camel.ProducerTemplate;
import org.joda.time.LocalDate;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;
import org.marc4j.utils.StaticTestRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("circulationBootstrap")
public class CirculationBootstrap extends AbstractBootstrap implements
		BootstrapBean, ItemData, BranchData, PatronCategoryData, RoleData {

	private static final Logger logger = LoggerFactory
			.getLogger(CirculationBootstrap.class);

	// TODO - Autowired producer template means you only do the work after camel
	// route instantiate
	@Autowired
	@Qualifier("integralProducerTemplate")
	private ProducerTemplate producerTemplate;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private MarcServiceFacadeImpl marcService;

	@Autowired
	private MaterialRepositoryImpl materialRepo;

	@Autowired
	private ItemRepositoryImpl itemRepo;

	@Autowired
	private PatronRepositoryImpl patronRepo;

	@Autowired
	private OfficerRepository officerRepo;

	@Autowired
	private ItemDatabasePopulator itemDatabasePopulator;

	@Autowired
	private PatronDatabasePopulator patronDatabasePopulator;

	@Autowired
	private OfficerDatabasePopulator officerDatabasePopulator;

	@Autowired
	private PatronItemEligibilityDatabasePopulator patronItemEligDatabasePopulator;

	@Autowired
	private ReceiptingTransactionCodeDatabasePopulator receiptingTransactionCodeDatabasePopulator;
	
	@Autowired
	private PatronItemEligibilityRepository patronItemEligRepo;
	
	@Autowired
	private PatronItemEligibilityLookup patronItemEligibilityLookup;
		
	private Record record;

	private int itemNo = 0;

	@Override
	public void init() throws Exception {
		record = StaticTestRecords.getSummerlandRecord();
		record = marcService.create("", "", record);
		System.out
				.println("getControlNumber=" + this.record.getControlNumber());
		Material material = materialRepo.findByMaterialNo(String
				.valueOf(this.record.getControlNumber()));
		Item openShelfItem = new Item("A000000001", OPEN_SHELF,
				LEVEL_ONE_MAIN_BRANCH, material, BOOK, ItemStatus.AVAILABLE,
				GOOD_CONDITION);
		itemRepo.save(openShelfItem);

		List<Record> records = setUpMaterials();
		
		List<Item> items = new ArrayList<Item>();
		for (Record record : records) {
			items.addAll(setUpItem(record, 2));
		}

		String patronId = "limsyenie";
		Officer officer = officerRepo.findByUsername(patronId);
		Patron limsyenie = patronRepo.findByUsername(patronId);
		for (int i = 0; i < 3; i++) {
			Item item = items.get(i);
			item.checkOut(officer, limsyenie, new Date());
		}
		
		prepareReservationRecord();
		
		
	}

	private Officer getLoginUser() {
		return securityService.getLoginUser();
	}

	public byte[] getResource(String path) throws IOException {
		return super.getResource("circulation/" + path);
	}

	private List<Item> setUpItem(Record record, int noOfItem) {
		List<Item> ctdocms = new ArrayList<Item>();
		Material material = materialRepo.findByMaterialNo(record
				.getControlNumber());
		for (int i = 0; i < noOfItem; i++) {
			itemNo++;
			String tempDocNo = String.format("%04d", itemNo);
			tempDocNo = "VUFIND" + tempDocNo;
			Item ctdocm = new Item(tempDocNo, OPEN_SHELF,
					LEVEL_ONE_MAIN_BRANCH, material, BOOK, ItemStatus.AVAILABLE,
					GOOD_CONDITION);
			itemRepo.save(ctdocm);
			ctdocms.add(ctdocm);
		}

		return ctdocms;
	}

	private List<Record> setUpMaterials() throws Exception {
		List<Record> records = getRecordsFromFile("marcRecords.mrc");
		List<Record> newRecords = new ArrayList<Record>();
		for (Record record : records) {
			record = marcService.create("", "", record);
			newRecords.add(record);
		}
		return newRecords;
	}

	private List<Record> getRecordsFromFile(String marcFile) throws IOException {

		List<Record> records = new ArrayList<Record>();

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(marcFile);
		Assert.notNull(inputStream);

		MarcStreamReader reader = new MarcStreamReader(inputStream);
		while (reader.hasNext()) {
			Record record = reader.next();
			records.add(record);
		}

		inputStream.close();
		return records;
	}
	
	private void prepareReservationRecord(){
		List<Item> items = itemRepo.findByItemStatus(ItemStatus.AVAILABLE, null);
		if (!items.isEmpty()){
			for (int i = 0; i<10; i++){
				String patronId = "limsyenie";
				Officer officer = officerRepo.findByUsername(patronId);
				Patron limsyenie = patronRepo.findByUsername(patronId);
				Patron siewmeeyee = patronRepo.findByUsername("siewmeeyee");
				siewmeeyee.setLastname("Siew");
				siewmeeyee.setFirstname("Mei Yee");
				patronRepo.save(siewmeeyee);
				
				Item item = items.get(i);
				LocalDate date = new LocalDate(new Date());
				Date checkOutDate = date.minusDays(30).toDate();
				
				item.checkOut(officer, limsyenie, checkOutDate);
				Date reserveDate = date.minusDays(2).toDate();
				item.reserve(officer, siewmeeyee, MAIN_BRANCH, checkOutDate);
				logger.debug("RESERVED ITEM = " + item);
				
				setUpFines(siewmeeyee, item);
			}
		}
		
		
		
	}
	
	private void setUpFines(Patron patron, Item item){
		BigDecimal rate = new BigDecimal("0.10");
		Fine fine = new Fine(1, -1, rate);
				
		Eligibility<PatronItemEligibility> eligibility = this.patronItemEligibilityLookup
				.lookup(patron, item);
		PatronItemEligibility patronItemElig = eligibility.getDomain();				
		patronItemElig.getFines().add(fine);		
		patronItemEligRepo.save(patronItemElig);
	}
	
}
