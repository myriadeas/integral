package my.com.myriadeas.integral.circulation.vufind.mapper.impl;

import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.service.PublicationYearRetrieverIntf;
import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.circulation.vufind.Hold;
import my.com.myriadeas.integral.circulation.vufind.mapper.HoldMapper;
import my.com.myriadeas.integral.circulation.vufind.mapper.VufindUtil;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holdMapper")
public class HoldMapperImpl implements HoldMapper {

	private static final Logger logger = org.slf4j.LoggerFactory
			.getLogger(HoldMapperImpl.class);
	
	private VufindUtil vufindUtil = new VufindUtil();
	private TitleRetrieverIntf titleRetriever;
	private PublicationYearRetrieverIntf publicationYearRetriever;	
	
	
	@Autowired
	public void setTitleRetriever(TitleRetrieverIntf titleRetriever) {
		this.titleRetriever = titleRetriever;
	}


	@Autowired
	public void setPublicationYearRetriever(
			PublicationYearRetrieverIntf publicationYearRetriever) {
		this.publicationYearRetriever = publicationYearRetriever;
	}



	@Override
	public Hold convertTo(ReservationTransaction reservationTransaction, int holdPosition) {
		logger.debug("Entering convertTo(ciresv={})", reservationTransaction);
		Hold hold = new Hold();
		if (reservationTransaction.getStatus().equals(ReservationStatus.AWAITING_COLLECTION)){
			hold.setAvailable(true);
		} else {
			hold.setAvailable(false);
		}
		hold.setCreate(vufindUtil.getDateTimeString(reservationTransaction.getReserveDateTime()));
		hold.setExpire(vufindUtil.getDateString(reservationTransaction.getScruitinyDateTime()));
		
		Material material = reservationTransaction.getMaterial();
		if (material != null){
			hold.setId(material.getMaterialNo());
		}	
		
		Item item = reservationTransaction.getItem();
		if (item != null){
			hold.setItem_id(item.getItemIdentifier());
			hold.setTitle(titleRetriever.getTitle(item));
			hold.setPublication_year(publicationYearRetriever.getPublicationYear(item));
		}		
		if (reservationTransaction.getBranch() != null){
			hold.setLocation(reservationTransaction.getBranch().getDescription());
		}
		hold.setPosition(holdPosition);		
		hold.setReqnum(reservationTransaction.getId().toString());		
		hold.setType("hold");
		//hold.setVolume("");
		logger.debug(
				"Leaving convertTo(). hold={}",
				hold);

		return hold;
		
	}
	
	/*
	private int getPosition(Ciresv ciresc){
		ciresvRepo.
	}
	*/
	
	/*
	@Override
	public Fines convertTo(Cicirc cicirc, OverdueItem overdueItem) {
		logger.debug("Entering convertTo(overdueItem={})", overdueItem);
		Fines fines = new Fines();
		fines.setAmount(vufindUtil.convertToPenny(overdueItem.getFines()));
		fines.setBalance(vufindUtil.convertToPenny(overdueItem.getBalance()));
		fines.setCheckout(vufindUtil.getDateTimeString(overdueItem.getCheckOutDateTime()));
		fines.setCreatedate(vufindUtil.getDateTimeString(overdueItem.getCreationDateTime()));
		fines.setDuedate(vufindUtil.getDateTimeString(overdueItem.getDueDateTime()));
		fines.setFine("Fines for late return.");
		fines.setId(cicirc.getCi02docno().getCt03matno().getCt02matno());
		
		logger.debug(
				"Leaving convertTo(). fines={}",
				fines);

		return fines;

	}
	*/
	
	
	
	
}
