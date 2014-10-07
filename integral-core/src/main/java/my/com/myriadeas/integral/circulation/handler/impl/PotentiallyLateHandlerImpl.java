package my.com.myriadeas.integral.circulation.handler.impl;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.circulation.CirculationEvents;
import my.com.myriadeas.integral.circulation.command.CheckInEventCommand;
import my.com.myriadeas.integral.circulation.command.GenerateFinesEventCommand;
import my.com.myriadeas.integral.circulation.command.ReleaseEventCommand;
import my.com.myriadeas.integral.circulation.command.RenewEventCommand;
import my.com.myriadeas.integral.circulation.service.AbstractCirculationService;
import my.com.myriadeas.integral.circulation.service.impl.OverdueItem;
import my.com.myriadeas.integral.circulation.service.impl.OverdueLoanFactory;
import my.com.myriadeas.integral.core.command.handler.CommandHandler;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("potentiallyLateHandler")
public class PotentiallyLateHandlerImpl extends AbstractCirculationService
		implements CommandHandler<CheckInEventCommand> {
	private static final Logger logger = LoggerFactory
			.getLogger(PotentiallyLateHandlerImpl.class);

	private OverdueLoanFactory overdueLoanFactory;

	private CirculationTransactionRepositoryImpl circulationTransactionRepo;

	@Autowired
	public void setOverdueLoanFactory(OverdueLoanFactory overdueLoanFactory) {
		this.overdueLoanFactory = overdueLoanFactory;
	}

	@Autowired
	public void setItem(ItemRepositoryImpl itemRepo) {
		this.itemRepo = itemRepo;
	}

	@Autowired
	public void setCirculationTransaction(
			CirculationTransactionRepositoryImpl circulationTransactionRepository) {
		this.circulationTransactionRepo = circulationTransactionRepository;
	}

	@Override
	public void handle(CheckInEventCommand command) {
		logger.debug("Entering handle(command={})", command);
		CirculationTransaction circulationTransaction = circulationTransactionRepo
				.findOne(command.getCirculationTransaction().getId());

		handle(circulationTransaction);
		
		logger.debug("Leaving handle()");
	}
	

	public void handleForRenewEvent(RenewEventCommand command) {
		logger.debug("Entering handleForRenewEvent (RenewEventCommand={})",
				command);
		CirculationTransaction circulationTransaction = circulationTransactionRepo
				.findOne(command.getCirculationTransaction().getId());

		handle(circulationTransaction);
		
		logger.debug("Leaving handleForRenewEvent(). ");
	}
	
	protected void handle(CirculationTransaction circulationTransaction){
		logger.debug("Entering handle (circulationTransaction={})",
				circulationTransaction);
		OverdueItem overdueItem = overdueLoanFactory
				.createOverdueItem(circulationTransaction);
		
		if (overdueItem.getFines().compareTo(new BigDecimal(0)) == 1){		
			FinesTransaction finesTransaction = overdueItem.createFinesTransaction();
			logger.debug("FinesTransaction={}", finesTransaction);
			if (finesTransaction != null) {
				publisher
						.publish(CirculationEvents.GENERATE_FINES,
								new GenerateFinesEventCommand(overdueItem,
										finesTransaction));
			}

		}
		
		logger.debug("Leaving handle(). ");
	}
}
