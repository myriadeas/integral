package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.circulation2.application.HoldingService;
import my.com.myriadeas.integral.circulation2.application.ReleaseHoldingCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holdingFacadeImpl")
public class HoldingFacadeImpl implements HoldingFacade {

	private HoldingService holdingService;

	@Autowired
	public void setHoldingService(HoldingService holdingService) {
		this.holdingService = holdingService;
	}

	@Override
	public ReleaseHoldingResponseDTO release(ReleaseHoldingRequestDTO request) {
		ReleaseHoldingCommand command = new ReleaseHoldingCommand(
				request.getItemIdentifier(), request.getItemCategoryCode());
		Long holdingId = this.holdingService.release(command);
		ReleaseHoldingResponseDTO response = new ReleaseHoldingResponseDTO(
				holdingId);
		return response;
	}

}
