package my.com.myriadeas.integral.circulation.mapper.impl;

import java.math.BigDecimal;
import java.util.List;

import my.com.myriadeas.integral.circulation.exception.UndefineItemStateException;
import my.com.myriadeas.integral.circulation.mapper.ItemInformationMapper;
import my.com.myriadeas.integral.circulation.response.ItemInformationResponse;
import my.com.myriadeas.integral.circulation.service.ReceiptingTransactionCodeService;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ceridwen.circulation.SIP.types.enumerations.CirculationStatus;
import com.ceridwen.circulation.SIP.types.enumerations.FeeType;
import com.ceridwen.circulation.SIP.types.enumerations.SecurityMarker;

@Service("itemInformationMapper")
public class ItemInformationMapperImpl implements ItemInformationMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(ItemInformationMapperImpl.class);
	
	private ReceiptingTransactionCodeService receiptingTransactionCodeService;
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;

	@Autowired
	public void setReservationTransactionRepo(
			ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}

	@Autowired
	public void setReceiptingTransactionCodeService(
			ReceiptingTransactionCodeService receiptingTransactionCodeService) {
		this.receiptingTransactionCodeService = receiptingTransactionCodeService;
	}



	@Override
	public ItemInformationResponse convertTo(Item item) {
		logger.debug("Entering convertTo(item={})", item);
		Assert.notNull(item);
		ItemInformationResponse itemInformationResponse = new ItemInformationResponse();
		
		CirculationStatus circulationStatus = getCirculationStatus(item.getItemStatus());
		itemInformationResponse.setCirculationStatus(circulationStatus);
		itemInformationResponse.setSecurityMarker(SecurityMarker.NONE);
		itemInformationResponse.setItemIdentifier(item.getItemIdentifier());
		itemInformationResponse.setDueDate(item.getDueDateTime());
		itemInformationResponse.setTitleIdentifier(item.getMaterial().getTitle());
		itemInformationResponse.setItem(item);
		
		if (item.isHold()){
			logger.debug("item is hold");
			ReservationTransaction reservationTransaction = item.getAwaitingCollectionReservationTransaction();
			itemInformationResponse.setAwaitingCollectionReservationTransaction(reservationTransaction);
		}
		
		
		logger.debug("Leaving itemInformation(). itemInformationResponse={}",
				itemInformationResponse);
		return itemInformationResponse;
	}

	@Override
	public ItemInformationResponse convertTo(Item item,
			CirculationTransaction circulationTransaction) {
		logger.debug("Entering convertTo(item={}, circulationTransaction={})", item, circulationTransaction);
		
		ItemInformationResponse itemInformationResponse = convertTo(item);
		
		List<FinesTransaction> finesTransactions = circulationTransaction.getFinesTransactions();
		itemInformationResponse.setFeeType(null);
		itemInformationResponse.setFeeAmount(null);
		if (!finesTransactions.isEmpty()) {
			logger.debug("finesTransactions ={}",
					finesTransactions);
			BigDecimal totalFines = new BigDecimal("0");
			for (FinesTransaction finesTransaction : finesTransactions){
				logger.debug("finesTransaction ={}", finesTransaction);				
				if (finesTransaction.getTransactionCode().equals(receiptingTransactionCodeService.getOverdueFines().getCode())){					
					totalFines = totalFines.add(finesTransaction.getAmount());
					logger.debug("totalFines={}", totalFines);
				}
			}
						
			if (totalFines.compareTo(new BigDecimal("0")) == 1){
				logger.debug("Setting fines information to response.");
				itemInformationResponse.setFeeAmount(totalFines.setScale(SCALE, ROUNDING_MODE).toString());
				itemInformationResponse.setFeeType(FeeType.OVERDUE);
				itemInformationResponse.setFinesTransaction(finesTransactions);
			}
		}
		logger.debug("Leaving convertTo(). itemInformationResponse={}",
				itemInformationResponse);
		return itemInformationResponse;
	}
	
	private FeeType getFeeType(String transactionCode){
		if (transactionCode.equalsIgnoreCase(receiptingTransactionCodeService.getOverdueFines()
				.getCode())) {
			return FeeType.OVERDUE;
		} else if (transactionCode.equalsIgnoreCase(receiptingTransactionCodeService.getProcessingFee()
				.getCode())) {
			return FeeType.PROCESSING;
		} else {
			return FeeType.OTHER;
		}
	}
	
	
	private CirculationStatus getCirculationStatus(ItemStatus statusCode) {
		if (statusCode.equals(ItemStatus.AVAILABLE)) {
			return CirculationStatus.AVAILABLE;
		} else if (statusCode.equals(ItemStatus.CIRCULATED)) {
			return CirculationStatus.CHARGED;
		} else if (statusCode.equals(ItemStatus.HOLD)) {
			return CirculationStatus.ON_HOLD_SHELF;
		} else if (statusCode.equals(ItemStatus.LOST)) {
			return CirculationStatus.LOST;
		} else if (statusCode.equals(ItemStatus.MISSING)) {
			return CirculationStatus.MISSING;
		} else if (statusCode.equals(ItemStatus.RECALLED)) {
			return CirculationStatus.RECALLED;			
		} else {
			
			//return CirculationStatus.OTHER;	
			
			// throw exception if undefined
			logger.warn("Undefined status discovered. It is either lag or process error. status code= "
					+ statusCode);
			throw new UndefineItemStateException("status code= " + statusCode
					+ " is undefine item state");
		}
	}

	
	
}
