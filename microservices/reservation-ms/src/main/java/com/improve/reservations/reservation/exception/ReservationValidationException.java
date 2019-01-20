package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class ReservationValidationException extends RuntimeException {

	public ReservationValidationException(final String exception) {
		super(exception);
	}

	public ReservationValidationException(final Throwable throwable) {
		super(throwable);
	}

}
