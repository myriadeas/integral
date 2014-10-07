package my.com.myriadeas.integral.circulation.service.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation.service.PublicationYearRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.springframework.stereotype.Service;

@Service("publicationYearRetriever")
public class PublicationYearRetrieverImpl implements PublicationYearRetrieverIntf {

	private Map<String, String> publicationYears;

	public PublicationYearRetrieverImpl() {
	}

	@Override
	public String getPublicationYear(Item item) {
		if (publicationYears == null) {
			init();
		}
		String publicationYear = "";

		if (item.getMaterial() != null
				&& item.getMaterial().getMaterialNo() != null) {
			publicationYear = publicationYears.get(item.getMaterial().getMaterialNo());
		}
		if (publicationYear == null || publicationYear.length() == 0) {
			publicationYear = "2001";
		}
		return publicationYear;
	}

	protected void init() {
		publicationYears = new HashMap<String, String>();
		publicationYears.put("0000000001", "2001");
		publicationYears.put("0000000002", "2002");
		publicationYears.put("0000000003", "2003");
		publicationYears.put("0000000004", "2004");
		publicationYears.put("0000000005", "2005");
		publicationYears.put("0000000006", "2006");
		publicationYears.put("0000000007", "2007");
		publicationYears.put("0000000008", "2008");
		publicationYears.put("0000000009", "2009");
		publicationYears.put("0000000010", "2010");
	}

}
