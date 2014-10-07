package my.com.myriadeas.integral.circulation.service.impl;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation.service.TitleRetrieverIntf;
import my.com.myriadeas.integral.data.jpa.domain.Item;

import org.springframework.stereotype.Service;

@Service("titleRetriever")
public class TitleRetrieverImpl implements TitleRetrieverIntf {

	private Map<String, String> titles;

	public TitleRetrieverImpl() {
	}

	@Override
	public String getTitle(Item item) {
		if (titles == null) {
			init();
		}
		String title = "";

		if (item.getMaterial() != null
				&& item.getMaterial().getMaterialNo() != null) {
			title = titles.get(item.getMaterial().getMaterialNo());
		}
		if (title == null || title.length() == 0) {
			title = "Doctor Sleep: A Novel by Stephen King";
		}
		return title;
	}

	protected void init() {
		titles = new HashMap<String, String>();
		titles.put("0000000001",
				"Diary of a Wimpy Kid: Hard Luck, Book 8 by Jeff Kinney");
		titles.put("0000000002",
				"The Brief Wondrous Life of Oscar Wao by Junot Díaz");
		titles.put("0000000003", "Middlesex: A Novel by Jeffrey Eugenides");
		titles.put("0000000004", "A Visit from the Goon Squad by Jennifer Egan");
		titles.put("0000000005", "Gilead: A Novel by Marilynne Robinson");
		titles.put("0000000006", "The Book Thief by Markus Zusak");
		titles.put("0000000007", "Laugh-Out-Loud Jokes for Kids by Rob Elliott");
		titles.put("0000000008",
				"King and Maxwell (King & Maxwell) by David Baldacci");
		titles.put("0000000009",
				"White Fire (Pendergast) by Douglas Preston, Lincoln Child");
		titles.put("0000000010", "Sycamore Row by John Grisham");
	}

}
