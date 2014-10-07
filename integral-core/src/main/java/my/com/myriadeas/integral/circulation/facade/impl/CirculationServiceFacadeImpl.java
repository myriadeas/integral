package my.com.myriadeas.integral.circulation.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.facade.CirculationServiceFacade;
import my.com.myriadeas.integral.circulation.mapper.CheckInMapper;
import my.com.myriadeas.integral.circulation.mapper.CheckOutMapper;
import my.com.myriadeas.integral.circulation.mapper.ItemInformationMapper;
import my.com.myriadeas.integral.circulation.mapper.RecallMapper;
import my.com.myriadeas.integral.circulation.mapper.ReleaseMapper;
import my.com.myriadeas.integral.circulation.mapper.RenewMapper;
import my.com.myriadeas.integral.circulation.mapper.ReserveMapper;
import my.com.myriadeas.integral.circulation.request.CheckInRequest;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.request.ItemInformationRequest;
import my.com.myriadeas.integral.circulation.request.RecallRequest;
import my.com.myriadeas.integral.circulation.request.ReleaseRequest;
import my.com.myriadeas.integral.circulation.request.RenewRequest;
import my.com.myriadeas.integral.circulation.request.ReserveRequest;
import my.com.myriadeas.integral.circulation.response.CheckInResponse;
import my.com.myriadeas.integral.circulation.response.CheckOutResponse;
import my.com.myriadeas.integral.circulation.response.ItemInformationResponse;
import my.com.myriadeas.integral.circulation.response.RecallResponse;
import my.com.myriadeas.integral.circulation.response.ReleaseResponse;
import my.com.myriadeas.integral.circulation.response.RenewResponse;
import my.com.myriadeas.integral.circulation.response.ReserveResponse;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;
import my.com.myriadeas.integral.circulation.service.RecallService;
import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.MaterialRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("circulationServiceFacade")
public class CirculationServiceFacadeImpl extends AbstractCirculationService implements CirculationServiceFacade {
	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(CirculationServiceFacadeImpl.class);

	private MaterialRepositoryImpl materialRepo;	
	private BranchRepositoryImpl branchRepo;
	private RecallService recallService;
	private CheckOutMapper checkOutMapper;	
	private CheckInMapper checkInMapper;	
	private RenewMapper renewMapper;	
	private ReserveMapper reserveMapper;	
	private RecallMapper recallMapper;
	private ReleaseMapper releaseMapper;
	private ItemInformationMapper itemInformationMapper;

	
	@Autowired
	public void setMaterialRepo(MaterialRepositoryImpl materialRepo) {
		this.materialRepo = materialRepo;
	}
	@Autowired
	public void setBranchRepo(BranchRepositoryImpl branchRepo) {
		this.branchRepo = branchRepo;
	}
		
	@Autowired
	public void setPatronRepo(PatronRepositoryImpl patronRepo) {
		this.patronRepo = patronRepo;
	}

	@Autowired
	public void setRecallService(RecallService recallService) {
		this.recallService = recallService;
	}	
	@Autowired
	public void setCheckOutMapper(CheckOutMapper checkOutMapper) {
		this.checkOutMapper = checkOutMapper;
	}
	@Autowired
	public void setCheckInMapper(CheckInMapper checkInMapper) {
		this.checkInMapper = checkInMapper;
	}
	@Autowired
	public void setRenewMapper(RenewMapper renewMapper) {
		this.renewMapper = renewMapper;
	}
	@Autowired
	public void setReserveMapper(ReserveMapper reserveMapper) {
		this.reserveMapper = reserveMapper;
	}
	@Autowired
	public void setRecallMapper(RecallMapper recallMapper) {
		this.recallMapper = recallMapper;
	}	
	@Autowired
	public void setReleaseMapper(ReleaseMapper releaseMapper) {
		this.releaseMapper = releaseMapper;
	}
	@Autowired
	public void setItemInformationMapper(ItemInformationMapper itemInformationMapper) {
		this.itemInformationMapper = itemInformationMapper;
	}
	private Item getItem(String itemIdentifier) {
		return itemRepo.findByItemIdentifier(itemIdentifier);
	}

	private Patron getPatron(String patronIdentifier) {
		return patronRepo.findByUsername(patronIdentifier);
	}

	@Override
	public CheckOutResponse checkout(CheckOutRequest checkOutRequest) {
		logger.debug("Entering checkout(checkOutRequest={})", checkOutRequest);
		Item item = getItem(checkOutRequest.getItemIdentifier());
		Patron patron = getPatron(checkOutRequest.getPatronIdentifier());
		Date checkOutDate = checkOutRequest.getTransactionDate();
		CirculationTransaction cicirc = item.checkOut(getOfficer(), patron, checkOutDate);		
		CheckOutResponse checkOutResponse = checkOutMapper
				.convertTo(cicirc);
		logger.debug("Leaving checkout(). checkOutResponse={}",
				checkOutResponse);
		return checkOutResponse;
	}

	@Override
	public CheckInResponse checkin(CheckInRequest checkInRequest) {
		logger.debug("Entering checkin(checkInRequest={})", checkInRequest);
		Item item = getItem(checkInRequest.getItemIdentifier());		
		Date checkInDate = checkInRequest.getTransactionDate();
		CirculationTransaction circulationTransaction = item.checkIn(getOfficer(), checkInDate);				
		CheckInResponse checkInResponse = checkInMapper.convertTo(circulationTransaction);
		logger.debug("Leaving checkin(). checkInResponse={}",
				checkInResponse);
		return checkInResponse;
	}
	

	@Override
	public RenewResponse renew(RenewRequest renewRequest) {
		logger.debug("Entering renew(renewRequest={})", renewRequest);
		Item item = getItem(renewRequest.getItemIdentifier());
		Date renewDateTime = renewRequest.getTransactionDate();
		Patron patron = item.getPatron();
		item.renew(getOfficer(), renewDateTime);
		List<CirculationTransaction> circulationTransactions = circulationTransactionRepo
				.findByPatronAndItemAndFlag(patron, item,
						CirculationTransactionFlag.CHARGED);
		Assert.notEmpty(circulationTransactions);
		CirculationTransaction circulationTransaction = circulationTransactions.get(0);
		RenewResponse renewResponse = renewMapper.convertTo(circulationTransaction);
		logger.debug("Leaving renew(). renewResponse={}",
				renewResponse);
		return renewResponse;
	}

	@Override
	public ReserveResponse reserveByMaterial(ReserveRequest reserveRequest) {
		logger.debug("Entering reserveByMaterial(reserveRequest={})", reserveRequest);
		Material material = materialRepo.findByMaterialNo(reserveRequest.getTitleIdentifier());
		Patron patron = patronRepo.findByUsername(reserveRequest.getPatronIdentifier());
		Branch pickUpBranch = branchRepo.findByCode(reserveRequest.getPickupLocation());
		Date reserveDateTime = reserveRequest.getTransactionDate();		
		ReservationTransaction reservationTransaction = material.reserve(getOfficer(), patron, pickUpBranch, reserveDateTime);
		ReserveResponse reserveResponse = reserveMapper.convertTo(reservationTransaction);
		logger.debug("Leaving reserveByMaterial(). reserveResponse={}",
				reserveResponse);
		return reserveResponse;
	}
	
	@Override
	public ReserveResponse reserveByAccession(ReserveRequest reserveRequest) {
		logger.debug("Entering reserveByAccession(reserveRequest={})", reserveRequest);
		Item item = getItem(reserveRequest.getItemIdentifier());
		Patron patron = patronRepo.findByUsername(reserveRequest.getPatronIdentifier());
		Branch pickUpBranch = branchRepo.findByCode(reserveRequest.getPickupLocation());
		Date reserveDateTime = reserveRequest.getTransactionDate();
		ReservationTransaction reservationTransaction = item.reserve(getOfficer(), patron, pickUpBranch, reserveDateTime);
		ReserveResponse reserveResponse = reserveMapper.convertTo(reservationTransaction);
		logger.debug("Leaving reserveByAccession(). reserveResponse={}",
				reserveResponse);
		return reserveResponse;
	}

	@Override
	public RecallResponse recall(RecallRequest recallRequest) {
		logger.debug("Entering recall(recallRequest={})", recallRequest);
		Patron patron = patronRepo.findByUsername(recallRequest
				.getPatronIdentifier());
		Item item = getItem(recallRequest.getItemIdentifier());
		Branch recallBranch = branchRepo.findByCode(recallRequest.getRecallBranch());
		Date recallDateTime = recallRequest.getTransactionDate();
		ReservationTransaction reservationTransaction = recallService.recall(patron, item, recallBranch, recallDateTime);
		RecallResponse recallResponse = recallMapper.convertTo(reservationTransaction);
		logger.debug("Leaving recall(). recallResponse={}",
				recallResponse);
		return recallResponse;
	}

	@Override
	public List<CirculationTransaction> getOnLoanItems(String patronId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractUser checkoutValidatePatron(String patronId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CirculationTransaction> getCirculationTransactionHistory(
			@PathParam("patronIdentifier") String patronId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ReleaseResponse release(ReleaseRequest releaseRequest) {
		logger.debug("Entering release(releaseRequest={})", releaseRequest);
		Item item = getItem(releaseRequest.getItemIdentifier());
		Assert.notNull(item);
		boolean isSuccessful = false;
		
		item.release(getOfficer(), new Date());
		isSuccessful = true;
		String message = getMessage("circulationServiceFacade.release.success", new Object[]{item.getItemIdentifier()},
				"Successfully released item " + item.getItemIdentifier() + ".");
		ReleaseResponse releaseResponse = releaseMapper.convertTo(item, isSuccessful, message);
		logger.debug("Leaving release(). releaseResponse={}",
				releaseResponse);
		return releaseResponse;
	}
	
	@Override
	public ItemInformationResponse getItemInformation(ItemInformationRequest request){
		logger.debug("Entering getItemInformation(request={})", request);
		Item item = getItem(request.getItemIdentifier());
		ItemInformationResponse itemInformationResponse = itemInformationMapper.convertTo(item);
		logger.debug("Leaving getItemInformation(). itemInformationResponse={}",
				itemInformationResponse);
		return itemInformationResponse;
	}
	
	@Override
	public ItemInformationResponse getItemInformation(String itemIdentifier, Long circulationTransactionId){
		logger.debug("Entering getItemInformation(itemIdentifier={}, circulationTransactionId={})", itemIdentifier, circulationTransactionId);
		Item item = getItem(itemIdentifier);		
		Assert.notNull(item);
		logger.debug("item.getItemStatus={}", item.getItemStatus());
		CirculationTransaction circulationTransaction = circulationTransactionRepo.findById(circulationTransactionId);
		Assert.notNull(circulationTransaction);
		ItemInformationResponse itemInformationResponse = itemInformationMapper.convertTo(item, circulationTransaction);
		
		logger.debug("Leaving getItemInformation(). itemInformationResponse={}",
				itemInformationResponse);
		return itemInformationResponse;
	}
	public PatronItemEligibility getPatronItemEligibility(String itemIdentifier, String username) {
		logger.debug("Entering getPatronItemEligibility(itemIdentifier={}, username={})", itemIdentifier, username);
		Item item = getItem(itemIdentifier);
		if(item == null) {
			return new PatronItemEligibility();
		}
		PatronItemEligibility patronItemEligibility= item.getPatronItemEligibility(username);
		logger.debug("Leaving getPatronItemEligibility(). patronItemEligibility={}", patronItemEligibility);
		return item.getPatronItemEligibility(username);
	}

}
