package my.com.myriadeas.integral.cataloguing2.interfaces.facade;

import java.util.List;
import java.util.Map;

public interface TemplateServiceFacade {

	public Map<String, List<String>> list(String library, String userid);

	public String create(String library, String userid, String templateid,
			String template);

	public String retrieve(String library, String userid, String templateid);

	public String update(String library, String userid, String templateid,
			String template);

	public String delete(String library, String userid, String templateid);

}
