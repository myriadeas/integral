package my.com.myriadeas.integral.mystic;

import java.io.IOException;
import java.util.Date;

import my.com.myriadeas.integral.circulation.response.CheckOutResponse;

import com.ceridwen.circulation.SIP.types.enumerations.CurrencyType;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFactory {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		CheckOutResponse checkOutResponse = new CheckOutResponse();
		checkOutResponse.setOk(true);
		checkOutResponse.setRenewalOk(false);		
		checkOutResponse.setMagneticMedia(false);
		checkOutResponse.setDesensitize(true);
		checkOutResponse.setDueDate(new Date());

		String message = "Item is successfully charged.";
		checkOutResponse.setScreenMessage(message);
		checkOutResponse.setPrintLine(message);

		checkOutResponse.setTransactionDate(new Date());
		checkOutResponse.setInstitutionId("1");
		
		checkOutResponse.setPatronIdentifier("1");
		checkOutResponse.setItemIdentifier("1");		
		//TODO
		//checkOutResponse.setTitleIdentifier(daoServiceFactory.getMaterialDaoService().getTitle(checkOutOutput.getMaterial().getMaterialNumber()));	
		String title = "Applied cryptograhy";
		
		checkOutResponse.setTitleIdentifier(title);
		checkOutResponse.setCurrencyType(CurrencyType.MALAYSIA_RINGGITS);
		checkOutResponse.setFeeAmount("0");
		System.out.println(objectMapper.writeValueAsString(checkOutResponse));

	}

}
