package my.com.myriadeas.integral.circulation.facade.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.circulation.facade.impl.VufindServiceFacadeImpl;
import my.com.myriadeas.integral.circulation.vufind.Fines;
import my.com.myriadeas.integral.circulation.vufind.Hold;
import my.com.myriadeas.integral.circulation.vufind.Holding;
import my.com.myriadeas.integral.circulation.vufind.Profile;
import my.com.myriadeas.integral.circulation.vufind.Status;
import my.com.myriadeas.integral.circulation.vufind.Transaction;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VufindServiceFacadeJsonTest {
	private VufindServiceFacadeImpl vufindServiceFacade = new VufindServiceFacadeImpl();
	
	@Test
	public void generateGetProfileJson() throws JsonGenerationException, JsonMappingException, IOException {
		
		Profile profile = new Profile();
		profile.setFirstname("Julian");
		profile.setLastname("Wong");
				
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(profile));
	}
	
	@Test
	public void generateGetStatusJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<Status> statuses = new ArrayList<Status>();
		Status status = new Status();
		status.setAvailability(true);
		status.setCallnumber("A12.352.2");
		statuses.add(status);
		statuses.add(status);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(statuses));
	}
	
	@Test
	public void generateGetStatusesJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<List<Status>> statusList = new ArrayList<List<Status>>();
		List<Status> statuses = new ArrayList<Status>();
		Status status = new Status();
		status.setAvailability(true);
		status.setCallnumber("A12.352.2");
		statuses.add(status);
		statuses.add(status);
		
		statusList.add(statuses);
		statusList.add(statuses);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(statusList));
	}
	
	@Test
	public void generateGetMyTransactionsJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setDuedate("01/01/2015 14:01:01");
		transaction.setItem_id("0000000001");
		transaction.setRenewable(true);
		transaction.setRenew(1);
		transactions.add(transaction);
		transactions.add(transaction);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(transactions));
	}
	
	@Test
	public void generateGetMyFinesJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<Fines> finesList = new ArrayList<Fines>();
		Fines fines = new Fines();
		fines.setAmount(300);
		fines.setCheckout("01/01/2012 14:01:01");
		finesList.add(fines);
		finesList.add(fines);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(finesList));
	}
	
	
	@Test
	public void generateGetMyHoldsJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<Hold> holds = new ArrayList<Hold>();
		Hold hold = new Hold();
		hold.setAvailable(true);
		hold.setItem_id("0000000001");
		hold.setPosition(1000);
		holds.add(hold);
		holds.add(hold);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(holds));
	}
	
	@Test
	public void generateRenewMyItemsJson() throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("success", true);				
		statusMap.put("new_date", "01/01/2015");
		statusMap.put("new_time", "");
		
		Map<String, Map<String, Object>> itemMap = new HashMap<String, Map<String, Object>>();		
		itemMap.put("000000001", statusMap);
		itemMap.put("000000002", statusMap);
		
		List<String> messages = new ArrayList<String>();
		messages.add("block message1");
		messages.add("block message2");
		Map<String, Object> finalMap = new HashMap<String, Object>();
		
		
		finalMap.put("blocks", messages);
		finalMap.put("details", itemMap);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(finalMap));
	}
	
	@Test
	public void generatePlaceHoldJson() throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("sysMessage", "Successfully place hold.");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(map));
	}
	
	@Test
	public void generateCancelHoldsJson() throws JsonGenerationException, JsonMappingException, IOException {
		
		Map<String, Map<String, Object>> itemMap = new HashMap<String, Map<String, Object>>();
		Map<String, Object> cancelHoldMap = new HashMap<String, Object>();		
		cancelHoldMap.put("success", true);
		cancelHoldMap.put("status", "hold_cancel_success");	
		itemMap.put("123", cancelHoldMap);
		itemMap.put("456", cancelHoldMap);
		
		Map<String, Object> finalMap = new HashMap<String, Object>();	
		finalMap.put("count", 2);
		finalMap.put("items", itemMap);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(finalMap));
	
		
	}
	
	@Test
	public void generateGetHoldingJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<Holding> holdings = new ArrayList<Holding>();
		Holding holding = new Holding();
		holding.setAvailability(true);
		holding.setBarcode("aaaaa");
		holdings.add(holding);
		holdings.add(holding);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(holdings));
	}
		
}
