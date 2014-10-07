package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.SMD;

public interface SmdData {
	SMD BOOK = new SMD("B", "Books");
	SMD VIDEO = new SMD("AV", "Audio & Video");
}
