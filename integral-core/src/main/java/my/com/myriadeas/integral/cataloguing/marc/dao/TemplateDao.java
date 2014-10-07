package my.com.myriadeas.integral.cataloguing.marc.dao;

import java.util.List;

public interface TemplateDao {
	public List<String> getAllTemplates(String library, String userid);
	public String getTemplate(String library, String userid, String templateId);
	public String createTemplate(String library, String userid, String templateId, String template);
	public String updateTemplate(String library, String userid, String templateId, String template);
	public String deleteTemplate(String library, String userid, String templateId);
}
