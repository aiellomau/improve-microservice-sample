package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class UnAvailabilityException extends ReservationValidationException {

	public UnAvailabilityException(final String exception) {
		super(exception);
	}

	public UnAvailabilityException(final Throwable throwable) {
		super(throwable);
	}

}
