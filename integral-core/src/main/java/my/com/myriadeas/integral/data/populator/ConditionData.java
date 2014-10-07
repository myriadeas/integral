package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Condition;

public interface ConditionData {
	Condition GOOD_CONDITION = new Condition("01", "GOOD CONDITION");
	Condition TORN = new Condition("02", "TORN");
	Condition MISSING_PAGE = new Condition("03", "MISSING PAGE");
	Condition DOG_EARED = new Condition("04", "DOG EARED");
}
