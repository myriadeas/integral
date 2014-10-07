package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

public interface ReservationTransactionData extends ItemData, PatronData {
	static final String RESERVE = "X";
	static final String AWAITING_COLLECTION = "A";
	ReservationTransaction MATERIAL_RESERVED_BY_SIEWMEEYEE = new ReservationTransaction(RESERVE, SIEWMEEYEE,
			ONHOLD_ITEM_1.getMaterial(), 1, MAIN_BRANCH);

	ReservationTransaction MATERIAL_RESERVED_BY_LIMSYENIE = new ReservationTransaction(RESERVE, LIMSYENIE,
			ONHOLD_ITEM_2.getMaterial(), 1, MAIN_BRANCH);

	ReservationTransaction MATERIAL_CIRCULATED_RESERVED = new ReservationTransaction(RESERVE, SIEWMEEYEE,
			CIRCULATED_ITEM_RED_SPOT_BOOK_1.getMaterial(), 1, MAIN_BRANCH);

}
