package my.com.myriadeas.integral.circulation.facade;

import java.util.List;

import javax.ws.rs.PathParam;

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
import my.com.myriadeas.integral.data.jpa.domain.AbstractUser;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;

/**
 * This is the interface that will list all possible process that expose to
 * public rest api. Implementation should able to translate request into
 * necessary data domain using respository and call necessarily service to
 * perform the job. Try to change this layer of implementation to support
 * different type of request / response
 * 
 * @author hutingung
 * 
 */
public interface CirculationServiceFacade {
	public CheckOutResponse checkout(CheckOutRequest checkOutRequest);

	public CheckInResponse checkin(CheckInRequest checkInRequest);

	public ReleaseResponse release(ReleaseRequest releaseRequest);

	public RenewResponse renew(RenewRequest renewRequest);

	public ReserveResponse reserveByMaterial(ReserveRequest reserveRequest);
	
	public ReserveResponse reserveByAccession(ReserveRequest reserveRequest);

	public RecallResponse recall(RecallRequest recallRequest);

	public List<CirculationTransaction> getOnLoanItems(String patronId);

	public AbstractUser checkoutValidatePatron(String patronId);

	
	//public ItemInformationResponse getCheckinItemInformation(String itemIdentifier, Long cicircIdentifier);

	public List<CirculationTransaction> getCirculationTransactionHistory(
			@PathParam("patronIdentifier") String patronId);
	
	public ItemInformationResponse getItemInformation(ItemInformationRequest request);
	
	public ItemInformationResponse getItemInformation(String itemIdentifier, Long circulationTransactionId);
}
