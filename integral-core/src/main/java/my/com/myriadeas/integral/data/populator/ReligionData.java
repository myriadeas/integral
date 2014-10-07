package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Religion;

public interface ReligionData {
	Religion HINDU = new Religion("HINDU", "Hindu");
	Religion ISLAM = new Religion("ISLAM", "Islam");
	Religion CHRISTIAN = new Religion("CHRISTIAN", "Christian");
}
