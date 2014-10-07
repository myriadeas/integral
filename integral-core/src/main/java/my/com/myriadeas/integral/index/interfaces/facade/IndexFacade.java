package my.com.myriadeas.integral.index.interfaces.facade;

import com.fasterxml.jackson.databind.JsonNode;

public interface IndexFacade {

	public JsonNode index(JsonNode request);
}
