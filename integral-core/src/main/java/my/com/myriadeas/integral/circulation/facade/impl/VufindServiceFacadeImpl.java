package my.com.myriadeas.integral.circulation.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.circulation.facade.VufindServiceFacade;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronValidator;
import my.com.myriadeas.integral.circulation.vufind.CancelHoldRequest;
import my.com.myriadeas.integral.circulation.vufind.Fines;
import my.com.myriadeas.integral.circulation.vufind.Hold;
import my.com.myriadeas.integral.circulation.vufind.Holding;
import my.com.myriadeas.integral.circulation.vufind.PatronLoginRequest;
import my.com.myriadeas.integral.circulation.vufind.PickUpLocation;
import my.com.myriadeas.integral.circulation.vufind.Profile;
import my.com.myriadeas.integral.circulation.vufind.RenewMyItemsRequest;
import my.com.myriadeas.integral.circulation.vufind.Status;
import my.com.myriadeas.integral.circulation.vufind.Transaction;
import my.com.myriadeas.integral.circulation.vufind.VufindPatron;
import my.com.myriadeas.integral.circulation.vufind.mapper.CancelHoldMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.HoldMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.HoldingMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.PickUpLocationMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.ProfileMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.RenewItemMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.StatusMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.TransactionMapper;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vufindServiceFacade")
public class VufindServiceFacadeImpl  extends AbstractCirculationService implements VufindServiceFacade {
	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(VufindServiceFacadeImpl.class);
	
	private StatusMapper statusMapper;
	private ProfileMapper profileMapper;
	private TransactionMapper transactionMapper;
	private HoldMapper holdMapper;
	private RenewItemMapper renewItemMapper;
	private CancelHoldMapper cancelHoldMapper;
	private HoldingMapper holdingMapper;
	private PickUpLocationMapper pickUpLocationMapper;
	
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;
	private BranchRepositoryImpl branchRepo;
	private MaterialRepositoryImpl materialRepo;
	
	private RenewPolicyItemValidator renewItemValidator;	
	private RenewPolicyPatronItemValidator renewPatronItemValidator;	
	private RenewPolicyPatronValidator renewPatronValidator;
	
	private ReservePolicyItemValidator reserveItemValidator;
	private RecallPolicyPatronItemValidator recallPatronItemValidator;
	private ReservePolicyPatronValidator reservePatronValidator;
		
	@Autowired
	public void setTransactionMapper(TransactionMapper transactionMapper) {
		this.transactionMapper = transactionMapper;
	}


	@Autowired
	public void setHoldMapper(HoldMapper holdMapper) {
		this.holdMapper = holdMapper;
	}
	
	@Autowired
	public void setHoldingMapper(HoldingMapper holdingMapper) {
		this.holdingMapper = holdingMapper;
	}

	
	@Autowired
	public void setCancelHoldMapper(CancelHoldMapper cancelHoldMapper) {
		this.cancelHoldMapper = cancelHoldMapper;
	}
	
	@Autowired
	public void setPickUpLocationMapper(PickUpLocationMapper pickUpLocationMapper) {
		this.pickUpLocationMapper = pickUpLocationMapper;
	}


	@Autowired
	public void setRenewItemMapper(RenewItemMapper renewItemMapper) {
		this.renewItemMapper = renewItemMapper;
	}

	@Autowired
	public void setReservationTransactionRepo(ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}


	
	@Autowired
	public void setMaterialRepo(MaterialRepositoryImpl materialRepo) {
		this.materialRepo = materialRepo;
	}


	@Autowired
	public void setBranchRepo(BranchRepositoryImpl branchRepo) {
		this.branchRepo = branchRepo;
	}


	@Autowired
	public void setRenewPatronValidator(RenewPolicyPatronValidator renewPatronValidator) {
		this.renewPatronValidator = renewPatronValidator;
	}



	@Autowired
	public void setStatusMapper(StatusMapper statusMapper) {
		this.statusMapper = statusMapper;
	}
	
	@Autowired
	public void setProfileMapper(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}
	
	
	
	@Autowired
	public void setRenewItemValidator(RenewPolicyItemValidator renewItemValidator) {
		this.renewItemValidator = renewItemValidator;
	}

	@Autowired
	public void setRenewPatronItemValidator(
			RenewPolicyPatronItemValidator renewPatronItemValidator) {
		this.renewPatronItemValidator = renewPatronItemValidator;
	}
	
	

	@Autowired
	public void setReservePolicyItemValidator(ReservePolicyItemValidator reserveItemValidator) {
		this.reserveItemValidator = reserveItemValidator;
	}

	@Autowired
	public void setRecallPatronItemValidator(
			RecallPolicyPatronItemValidator recallPatronItemValidator) {
		this.recallPatronItemValidator = recallPatronItemValidator;
	}

	@Autowired
	public void setReservePolicyPatronValidator(
			ReservePolicyPatronValidator reservePatronValidator) {
		this.reservePatronValidator = reservePatronValidator;
	}


	@Override
	public List<Status> getStatus(String bibliographicId){
		logger.debug("Entering getStatus(bibliographicId={})", bibliographicId);
		Material material = materialRepo.findByMaterialNo(bibliographicId);
		List<Item> items = material.getItems();
		logger.debug("List of item=()", items);
		List<Status> statuses = new ArrayList<Status>();
		for (Item item: items){
			logger.debug("Perform conversion");
			Status status = statusMapper.convertTo(item);
			statuses.add(status);
		}
		logger.debug("Leaving getStatus(). statuses={}", statuses);
		return statuses;
	}

	@Override
	public List<List<Status>> getStatuses(List<String> bibliographicIds) {
		logger.debug("Entering getStatuses(bibliographicIds={})", bibliographicIds);
		List<List<Status>> statusesList = new ArrayList<List<Status>>();
		for(String bibliographicId: bibliographicIds){
			List<Status> statuses = this.getStatus(bibliographicId);			
			statusesList.add(statuses);
		}
		logger.debug("Leaving getStatuses(). statusesList={}", statusesList);
		return statusesList;
	}

	@Override
	public Profile getMyProfile(String username) {
		logger.debug("Entering getMyProfile(username={})", username);
		Patron patron = patronRepo.findByUsername(username);
		Profile profile = null;
		if (patron != null){
			logger.debug("Perform conversion");
			profile = profileMapper.convertTo(patron);
		} else {
			logger.debug("Invalid username(" + username + ")");
		}
		logger.debug("Leaving getMyProfile(). profile={}", profile);
		return profile;
	}

	@Override
	public List<Transaction> getMyTransactions(String username) {
		logger.debug("Entering getMyTransactions(username={})", username);
		List<Transaction> transactions = new ArrayList<Transaction>();
		Patron patron = patronRepo.findByUsername(username);
		if (patron != null){
			logger.debug("Getting circulated circulation transaction");
			List<CirculationTransaction> circulationTransactions = patron.getCirculationTransactionsWithFlagCharged();
			logger.debug("Circulated circulation transaction = " + circulationTransactions);
			for (CirculationTransaction circulationTransaction: circulationTransactions){
				logger.debug("Perform conversion");
				Transaction transaction = transactionMapper.convertTo(circulationTransaction);
				transactions.add(transaction);
			}
		} else {
			logger.debug("Invalid username(" + username + ")");
		}
		logger.debug("Leaving getMyTransactions(). transactions={}", transactions);
		return transactions;
	}


	@Override
	public List<Fines> getMyFines(String username) {
		logger.debug("Entering getMyFines(username={})", username);
		// TODO Auto-generated method stub
		//Glpatr patron = glpatrRepo.findByGl14patr(patronId);
		logger.debug("Leaving getMyFines(). holds={}", "");
		return null;
	}



	@Override
	public List<Hold> getMyHolds(String username) {
		logger.debug("Entering getMyHolds(username={})", username);
		List<Hold> holds = new ArrayList<Hold>();
		Patron patron = patronRepo.findByUsername(username);
		if (patron != null){
			logger.debug("Finding reservationTransaction");
			List<ReservationTransaction> reservationTransactions = reservationTransactionRepo.findByPatron(patron);		
			for (ReservationTransaction reservationTransaction: reservationTransactions){
				logger.debug("Perform conversion");
				Hold hold = holdMapper.convertTo(reservationTransaction, getHoldPosition(reservationTransaction));
				holds.add(hold);
			}
		} else {
			logger.debug("Invalid username(" + username + ")");
		}
		logger.debug("Leaving getMyHolds(). holds={}", holds);
		return holds;
	}
	
	private int getHoldPosition(ReservationTransaction reservationTransaction){
		logger.debug("Entering getHoldPosition(reservationTransaction={})", reservationTransaction);
		List<ReservationTransaction> reservationTransactions = reservationTransactionRepo.findByMaterialOrderByPriorityWeight(reservationTransaction.getMaterial());
		int position = 0;
		for (int i=0; i<reservationTransactions.size(); i++){
			ReservationTransaction reservationTransactionTemp = reservationTransactions.get(i);
			if (reservationTransactionTemp.getId() == reservationTransaction.getId()){
				position = i + 1;
				break;
			}
		}
		logger.debug("Leaving getHoldPosition(). position={}", position);
		return position;
	}



	@Override
	public String getRenewDetails(String itemId) {
		logger.debug("Entering getRenewDetails(itemId={})", itemId);
		
		long circulationTransactionId = 0;
		Item item = itemRepo.findByItemIdentifier(itemId);
		if (item != null){
			logger.debug("Finding circulationTransaction");
			List<CirculationTransaction> circulationTransactions = circulationTransactionRepo.findByPatronAndItemAndFlag(item.getPatron(), 
					item, "C");
			if (circulationTransactions != null && !circulationTransactions.isEmpty()){
				circulationTransactionId = circulationTransactions.get(0).getId();
			}
			
		} else {
			logger.debug("Invalid itemId(" + itemId + ")");
		}
		logger.debug("Leaving getRenewDetails(). circulationTransactionId={}", circulationTransactionId);
		return Long.toString(circulationTransactionId);
	}


	
	
	
	@Override
	public Map<String, Object> renewMyItems(RenewMyItemsRequest request) {
		String username = request.getUsername();
		List<Long> circulationTransactionIds = request.getCirculationTransactionIds();
		logger.debug("Entering renewMyItems(username={}, circulationTransactionIds={})",
				new Object[] { username, circulationTransactionIds});
		
		Map<String, Object> finalMap = new HashMap<String, Object>();		
		Patron patron = patronRepo.findByUsername(username);				
		if (patron == null){
			logger.debug("Invalid username(" + username + ")");
			return finalMap;
		}
		List<String> messages = new ArrayList<String>();		
		
		try{
			logger.debug("Validate patron");
			renewPatronValidator.validate(patron);
			Map<String, Map<String, Object>> itemMap = new HashMap<String, Map<String, Object>>();
			for (Long circulationTransactionId: circulationTransactionIds){				
				CirculationTransaction circulationTransaction = circulationTransactionRepo.findById(circulationTransactionId);		
				Map<String, Object> statusMap = new HashMap<String, Object>();
				if (circulationTransaction != null){			
					Item item = circulationTransaction.getItem();
					try{						
						logger.debug("Validate item");
						renewItemValidator.validate(item);
						logger.debug("Validate patron, item");
						renewPatronItemValidator.validate(patron, item);
						
						logger.debug("Perform renewal");
						CirculationTransaction renewCirculationTransaction = item.renew(getOfficer(), new DateTime().toDate());
						String message = getMessage("vufindServiceFacade.renew.success", 
								"Successfully renewed.");
						logger.debug("Perform conversion");
						statusMap = renewItemMapper.convertTo(renewCirculationTransaction, true, message);						
					} catch (Exception e){
						logger.debug(e.getMessage());
						String message = getMessage("vufindServiceFacade.renew.exception", new Object[]{e.getMessage()},
								e.getMessage());
						statusMap = renewItemMapper.convertTo(circulationTransaction, false, message);						
					}
					itemMap.put(item.getItemIdentifier(), statusMap);
				} else {
					logger.debug("Invalid circulationTransactionId(" + circulationTransactionId + ")");
				}
								
			}
			finalMap.put("blocks", messages);
			finalMap.put("details", itemMap);
		} catch (Exception e){				
			logger.debug(e.getMessage());
			messages.add(e.getMessage());
			finalMap.put("blocks", messages);
		}
		
		logger.debug("Leaving renewMyItems(). map={}", finalMap);
		return finalMap;
	}



	@Override
	public Map<String, Object> placeHold(String username, String itemId, String pickUpBranchCode) {
		logger.debug("Entering placeHold(username={}, itemId={}, pickUpBranch={})", 
				new Object[] { username, itemId, pickUpBranchCode });
		Map<String, Object> map = new HashMap<String, Object>();
		Patron patron = patronRepo.findByUsername(username);
		Item item = itemRepo.findByItemIdentifier(itemId);		
		
				
		if (patron == null){
			logger.debug("Invalid username(" + username + ")");
			return map;
		}
		if (item == null){
			logger.debug("Invalid itemId(" + itemId + ")");
			return map;
		}
		
		Branch branch = patron.getBranch();
		if (pickUpBranchCode != null || pickUpBranchCode != ""){
			Branch pickUpBranch = branchRepo.findByCode(pickUpBranchCode);
			if (pickUpBranch != null){
				branch = pickUpBranch;
			}
		}
		
		try{
			logger.debug("Validate patron");
			reservePatronValidator.validate(patron);	
			logger.debug("Validate item");
			reserveItemValidator.validate(item);
			logger.debug("Validate patron, item");
			recallPatronItemValidator.validate(patron, item);
			
			logger.debug("Perform reservation by accession");
			item.reserve(getOfficer(), patron, branch, new DateTime().toDate());
			map.put("success", true);
			map.put("sysMessage", "Successfully reserved.");
		} catch (Exception e){			
			logger.debug(e.getMessage());						
			map.put("success", false);
			map.put("sysMessage", e.getMessage());
		}
		
		logger.debug("Leaving placeHold(). map={}", map);
		
		return map;
	}


	@Override
	public Map<String, Object> cancelHolds(CancelHoldRequest request) {
		logger.debug("Entering cancelHolds(CancelHoldRequest={})", request);
		
		String username = request.getUsername();
		List<String> reservationTransactionIds = request.getReservationTransactionIds();
		Map<String, Object> finalMap = new HashMap<String, Object>();		
		Patron patron = patronRepo.findByUsername(username);
		if (patron == null){
			logger.debug("Invalid username(" + username +")");
			return finalMap;
		}
		
		int count = 0;
		Map<String, Map<String, Object>> itemMap = new HashMap<String, Map<String, Object>>();
		
		for (String reservationTransactionId: reservationTransactionIds){
			ReservationTransaction reservationTransaction = reservationTransactionRepo.findById(Long.parseLong(reservationTransactionId));
			if (reservationTransaction != null){				
				Item item = reservationTransaction.getItem();
				Map<String, Object> cancelHoldMap = new HashMap<String, Object>();
				try{
					logger.debug("Perform cancel reservation");
					reservationTransactionRepo.delete(reservationTransaction);
					count++;
					logger.debug("Perform conversion for successful cancellation");
					String message = getMessage("vufindServiceFacade.cancelHolds.success", "Successfully cancel hold.");
					cancelHoldMap = cancelHoldMapper.convertTo(true, message);
				} catch (Exception e){
					logger.debug("Perform conversion for failure cancellation");
					String message = getMessage("vufindServiceFacade.cancelHolds.exception", new Object[]{e.getMessage()}, e.getMessage());
					cancelHoldMap = cancelHoldMapper.convertTo(false, message);
				}
				itemMap.put((item != null ? item.getItemIdentifier() : "<no ct03docno>"), cancelHoldMap);
			} else {
				logger.debug("Invalid reservationTransactionId(" + reservationTransactionId +")");
			}
		}
		
		finalMap.put("count", count);
		finalMap.put("items", itemMap);
		logger.debug("Leaving cancelHolds(). map={}", finalMap);
		return finalMap;
	}


	@Override
	public List<Holding> getHolding(String username, String bibliographicId) {
		logger.debug("Entering getHolding(bibliographicId={})", bibliographicId);
		List<Holding> holdings = new ArrayList<Holding>();
		logger.debug("Getting list of item");
		Patron patron = patronRepo.findByUsername(username);	
		Material material = materialRepo.findByMaterialNo(bibliographicId);
		List<Item> items = material.getItems();
		for (Item item: items){
			logger.debug("Perform conversion");
			Holding holding = holdingMapper.convertTo(patron, item);
			holdings.add(holding);
		}		
		logger.debug("Leaving getHolding(). holdings={}", holdings);
		return holdings;
	}


	@Override
	public List<PickUpLocation> getPickUpLocations() {
		logger.debug("Entering getPickUpLocations()");
		List<PickUpLocation> pickUpLocations = new ArrayList<PickUpLocation>();
		List<Branch> branches = branchRepo.findAll();
		
		for (Branch branch: branches){
			PickUpLocation pickUpLocation = pickUpLocationMapper.convertTo(branch);
			pickUpLocations.add(pickUpLocation);
		}
		logger.debug("Leaving getPickUpLocations(). pickUpLocations={}", pickUpLocations);
		return pickUpLocations;
	}


	@Override
	public VufindPatron patronLogin(PatronLoginRequest request) {
		logger.debug("Entering patronLogin(patronLoginRequest={})", request);
		
		String username = request.getUsername();
		String password = request.getPassword();
		
		VufindPatron vufindPatron = new VufindPatron();
		Patron patron = patronRepo.findByUsername(username);
		if (patron != null){
			if (patron.getPassword().equals(password)){
				logger.debug("Valid patron.");
				vufindPatron.setCat_password(patron.getPassword());
				vufindPatron.setCat_username(patron.getUsername());
				vufindPatron.setEmail(patron.getEmail());
				vufindPatron.setFirstname(patron.getFirstname());
				vufindPatron.setId(patron.getUsername());
				vufindPatron.setLastname(patron.getLastname());
			} else {
				logger.debug("Password not matched.");
			}
		} else {
			logger.debug("Patron not found.");
		}
		
		logger.debug("Leaving patronLogin(). vufindPatron={}", vufindPatron);
		return vufindPatron;
	}

}
