package my.com.myriadeas.integral.circulation.facade;

import java.util.List;
import java.util.Map;

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


public interface VufindServiceFacade {
	public List<Status> getStatus(String bibliographicId);
	public List<List<Status>> getStatuses(List<String> bibliographicIds);
	public Profile getMyProfile(String username);
	public List<Transaction> getMyTransactions(String username);
	public List<Fines> getMyFines(String username);
	public List<Hold> getMyHolds(String username);
	public String getRenewDetails(String itemId);
	public Map<String, Object> renewMyItems(RenewMyItemsRequest request);
	public Map<String, Object> placeHold(String username, String itemId, String pickUpBranch);
	public Map<String, Object> cancelHolds(CancelHoldRequest request);
	public List<Holding> getHolding(String username, String bibliographicId);
	public List<PickUpLocation> getPickUpLocations();
	public VufindPatron patronLogin(PatronLoginRequest request);
	
	/*	
	public Map<String, String> renewMyItemsLink(String patronId);	
	public String getCancelHoldDetails(String patronId, String itemId);
	public Map<String, String> cancelHoldLinks(String patronId);
	
	
	*/
}
