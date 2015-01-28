package my.com.myriadeas.integral.item.query.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.item.AbstractItemIntegrationTest;
import my.com.myriadeas.integral.item.query.domain.ItemDisplay;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemReadServiceImplTest extends AbstractItemIntegrationTest {

	@Autowired
	private ItemReadService itemReadService;
	
	@Test
	public void testConstructItemDisplay() throws JsonGenerationException, JsonMappingException, IOException {
		
		ItemDisplay item = new ItemDisplay("item00001", "rd00001", "Insanely Simple", "Ken ",null);
		System.out.println("item=" + item);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("item=" + mapper.writeValueAsString(item));
	}

}
