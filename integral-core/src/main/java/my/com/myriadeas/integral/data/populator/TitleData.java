package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Title;

public interface TitleData {
	Title MISTER = new Title("MR", "Mr");
	Title MADAM = new Title("MDM", "Madam");
	Title MISS = new Title("MS", "Miss");
}
