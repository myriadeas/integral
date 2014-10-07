package my.com.myriadeas.marc.dao.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;
import my.com.myriadeas.integral.cataloguing.config.DevConfig;
import my.com.myriadeas.integral.cataloguing.marc.file.TemplateJsonImpl;

public class TemplateJsonImplTest extends TestCase {

	@Autowired
	private TemplateJsonImpl dao;
	
	private static final String library = "data";
	private static final String userId = "template";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new TemplateJsonImpl();
	}

	public void testGetAllTemplates() {
		List<String> list = dao.getAllTemplates(library, userId);
		assertNotNull(list);
		System.out.println(list);
	}

	public void testGetRecord() {
		String recordId = "669564";
		String json = dao.getTemplate(library, userId, recordId);
		assertNotNull(json);
		System.out.println("Retrieved template");
		System.out.println("------------------");
		System.out.println(json);
	}

}
