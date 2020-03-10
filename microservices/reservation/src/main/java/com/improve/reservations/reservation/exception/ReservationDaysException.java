package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class ReservationDaysException extends ReservationValidationException {

	public ReservationDaysException(final String exception) {
		super(exception);
	}

	public ReservationDaysException(final Throwable throwable) {
		super(throwable);
	}

}
