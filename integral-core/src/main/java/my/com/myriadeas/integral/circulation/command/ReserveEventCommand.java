package my.com.myriadeas.integral.circulation.command;


import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;


public class ReserveEventCommand {

	private ReservationTransaction reservationTransaction;

	public ReserveEventCommand(ReservationTransaction reservationTransaction) {
		this.reservationTransaction = reservationTransaction;
	}

	public ReservationTransaction getReservationTransaction() {
		return reservationTransaction;
	}

	public void setReservationTransaction(ReservationTransaction reservationTransaction) {
		this.reservationTransaction = reservationTransaction;
	}

}
