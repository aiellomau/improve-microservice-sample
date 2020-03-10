package com.improve.reservations.reservation.exception;

@SuppressWarnings("serial")
public class AnticipateDaysOutException extends ReservationValidationException {

	public AnticipateDaysOutException(final String exception) {
		super(exception);
	}

	public AnticipateDaysOutException(final Throwable throwable) {
		super(throwable);
	}

}
