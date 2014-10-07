package my.com.myriadeas.integral.data.jpa.domain;

import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reservationTransactionFactory")
public class ReservationTransactionFactory extends AbstractCirculationService {
	private static final Logger logger = LoggerFactory
			.getLogger(ReservationTransactionFactory.class);
	
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;
	
	@Autowired
	public void setReservationTransactionRepo(
			ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}




	public ReservationTransaction createReservationTransactionByItem(Item item){
		logger.debug(
				"Entering createReservationTransactionByItem(item={})", new Object[] {
						item});
		ReservationTransaction reservationTransaction = populateReservationTransaction(item);
		reservationTransactionRepo.save(reservationTransaction);		
		logger.debug("Leaving createReservationTransactionByItem(). reservationTransaction={}", reservationTransaction);
		return reservationTransaction;
	}
	
	
	public ReservationTransaction createReservationTransactionByMaterial(Material material){
		logger.debug(
				"Entering createReservationTransactionByMaterial(material={})", new Object[] {
						material});
		ReservationTransaction reservationTransaction = populateReservationTransaction(material);
		reservationTransactionRepo.save(reservationTransaction);	
		logger.debug("Leaving createReservationTransactionByMaterial(). reservationTransaction={}", reservationTransaction);
		return reservationTransaction;
	}
	
	
	
	protected ReservationTransaction populateReservationTransaction(Item item) {
		logger.debug(
				"Entering populateReservationTransaction(item={})", new Object[] {
						item});

		ReservationTransaction reservationTransaction = new ReservationTransaction();
		reservationTransaction.setPatron(item.getReserver());
		reservationTransaction.setStatus(ReservationStatus.RESERVE);
		reservationTransaction.setMaterial(item.getMaterial());
		reservationTransaction.setItem(item);
		reservationTransaction.setBranch(item.getReserverPickUpBranch());
		reservationTransaction.setReserveDateTime(item.getReserveDateTime());
		reservationTransaction.setPriorityWeight(getPriority(item.getMaterial()));		
		reservationTransaction.setOfficer(item.getCirculationOfficer());
		
		
		logger.debug("Leaving populateReservationTransaction(). reservationTransaction={}", reservationTransaction);
		
		return reservationTransaction;
		
	}
	
	protected ReservationTransaction populateReservationTransaction(Material material) {
		logger.debug(
				"Entering populateReservationTransaction(material={})", new Object[] {
						material});

		ReservationTransaction reservationTransaction = new ReservationTransaction();
		reservationTransaction.setPatron(material.getReserver());
		reservationTransaction.setStatus(ReservationStatus.RESERVE);
		reservationTransaction.setMaterial(material);
		reservationTransaction.setBranch(material.getReserverPickUpBranch());
		reservationTransaction.setReserveDateTime(material.getReserveDateTime());
		reservationTransaction.setPriorityWeight(getPriority(material));	
		reservationTransaction.setOfficer(material.getReserveOfficer());
				
		logger.debug("Leaving populateReservationTransaction(). reservationTransaction={}", reservationTransaction);
		
		return reservationTransaction;
		
	}
	
	protected int getPriority(Material ctmatm){
		int maxWeight = getMaxWeight(ctmatm);		
		return maxWeight + 1000;				
	}
	
	protected int getMaxWeight(Material ctmatm){
		Integer maxWeight =  reservationTransactionRepo.findMaxPriorityWeightByMaterial(ctmatm);
		if (maxWeight == null){
			maxWeight = new Integer(0);
		}
		return maxWeight;
	}
	
}
