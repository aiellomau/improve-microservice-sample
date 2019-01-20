package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class MaxDaysExcedeedException extends ReservationValidationException {

	public MaxDaysExcedeedException(final String exception) {
		super(exception);
	}

	public MaxDaysExcedeedException(final Throwable throwable) {
		super(throwable);
	}

}
