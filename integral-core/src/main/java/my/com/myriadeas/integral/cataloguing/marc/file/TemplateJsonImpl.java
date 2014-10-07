package my.com.myriadeas.integral.cataloguing.marc.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import my.com.myriadeas.integral.cataloguing.marc.dao.TemplateDao;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "templateJsonImpl")
public class TemplateJsonImpl implements TemplateDao {

	private static final String JSONFILE_EXT = "json";

	@Value("${template.home}")
	private String templateHome;

	private Logger logger = LoggerFactory.getLogger(TemplateJsonImpl.class);

	public List<String> getAllTemplates(String library, String userid) {
		String workspace = getTemplateHome() + library + File.separatorChar + userid + File.separatorChar + "template";
		logger.debug("Using folder=" + workspace);
		File folder = new File(workspace);
		try {
			if (!folder.exists()) {
				FileUtils.forceMkdir(folder);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> listId = new ArrayList<String>();
		if (folder.list() != null) {
			String fileExt = "." + JSONFILE_EXT;
			for (String name : folder.list()) {
				if (name.lastIndexOf(fileExt) == name.length() - fileExt.length()) {
					listId.add(name.substring(0,
							name.lastIndexOf(fileExt)));
				}
			}
		}
		Collections.sort(listId);
		System.out.println(templateHome);
		logger.debug("Leaving getAllTemplates(). listId=" + listId);
		return listId;
	}

	public String getTemplate(String library, String userid, String templateId) {
		StringBuffer jsonFile = new StringBuffer(1000);
		
		String srcFile = getRecordPath(library, userid, templateId);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(srcFile));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
				String readData = String.valueOf(buf, 0, numRead);
				jsonFile.append(readData);
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found " + srcFile);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return jsonFile.toString();
	}

	public String createTemplate(String library, String userid, String templateId, String template) {
		String srcFile = getRecordPath(library, userid, templateId);
		try {
			FileWriter fw = new FileWriter(srcFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(template);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return template;
	}

	public String updateTemplate(String library, String userid,
			String templateId, String template) {
		return createTemplate(library, userid, templateId, template);
	}

	public String deleteTemplate(String library, String userid,
			String templateId) {
		String json = getTemplate(library, userid, templateId);
		String destFile = getRecordPath(library, userid, templateId);
		if (!FileUtils.deleteQuietly(new File(destFile))) {
			logger.error("Fail to delete template: " + destFile);
		}
		return json;
	}

	public void setTemplateHome(String templateHome) {
		this.templateHome = templateHome;
		if (!this.templateHome.endsWith(File.separator)) {
			this.templateHome += File.separator;
		}
	}

	public String getTemplateHome() {
		return templateHome;
	}

	protected String getRecordPath(String library, String userId, String recordId) {
		return getPath(library, userId, recordId, JSONFILE_EXT);
	}
	
	protected String getPath(String library, String userId, String recordId, String extension) {
		if (recordId != null && extension != null) {
			return getFullPath(library, userId, recordId + "." + extension);
		} else {
			return "";
		}
	}

	protected String getFullPath(String library, String userId, String filename) {
		if (getLocation(library, userId) != null && filename != null) {
			return getLocation(library, userId) + filename;
		} else {
			return "";
		}
	}

	protected String getLocation(String library, String userId) {
		return getTemplateHome() + library + File.separatorChar + userId  + File.separatorChar + "template" + File.separatorChar;
	}

}
