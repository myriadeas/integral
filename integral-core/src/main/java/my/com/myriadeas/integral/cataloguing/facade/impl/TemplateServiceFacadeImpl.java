package my.com.myriadeas.integral.cataloguing.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.cataloguing.facade.TemplateServiceFacade;
import my.com.myriadeas.integral.cataloguing.marc.dao.TemplateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "templateService")
public class TemplateServiceFacadeImpl implements TemplateServiceFacade {

	@Autowired
	private TemplateDao templateDao;

	public Map<String, List<String>> list(String library, String userid) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("templates", templateDao.getAllTemplates(library, userid));
		return map;
	}

	public String create(String library, String userid, String templateid,
			String template) {
		return templateDao
				.createTemplate(library, userid, templateid, template);
	}

	public String retrieve(String library, String userid, String templateid) {
		return templateDao.getTemplate(library, userid, templateid);
	}

	public String update(String library, String userid, String templateid,
			String template) {
		return templateDao
				.updateTemplate(library, userid, templateid, template);
	}

	public String delete(String library, String userid, String templateid) {
		return templateDao.deleteTemplate(library, userid, templateid);
	}

}
