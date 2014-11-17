package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.circulation2.application.HoldingService;
import my.com.myriadeas.integral.circulation2.application.NewHoldingCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holdingFacadeImpl")
public class HoldingFacadeImpl implements HoldingFacade {

	private HoldingService holdingService;

	@Override
	public NewHoldingResponseDTO newHolding(NewHoldingRequestDTO request) {
		NewHoldingCommand command = new NewHoldingCommand(
				request.getItemIdentifier(), request.getItemCategoryCode());
		Long holdingId = holdingService.newHolding(command);
		NewHoldingResponseDTO response = new NewHoldingResponseDTO(holdingId);
		return response;
	}

	@Autowired
	public void setHoldingService(HoldingService holdingService) {
		this.holdingService = holdingService;
	}

}
