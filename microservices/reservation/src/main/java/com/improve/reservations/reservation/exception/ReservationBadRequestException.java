package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class ReservationBadRequestException extends RuntimeException {

	public ReservationBadRequestException(final String exception) {
		super(exception);
	}

	public ReservationBadRequestException(final Throwable throwable) {
		super(throwable);
	}

}
