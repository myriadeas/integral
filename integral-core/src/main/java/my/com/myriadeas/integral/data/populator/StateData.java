package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.State;

public interface StateData {
	State SELANGOR = new State("SGR", "Selangor");
	State KUALA_LUMPUR = new State("KUL", "Kuala Lumpur");
	State PENANG = new State("PNG", "Penang");
}
