package com.improve.reservations.reservation.service.validation;

import java.util.ArrayList;
import java.util.List;

public class ReservationErrors {

	private final List<ReservationError> errors = new ArrayList<>();

	public List<ReservationError> getErrors() {
		return errors;
	}

	public ReservationErrors append(final ReservationError reservationError) {
		this.errors.add(reservationError);
		return this;
	}

	public int size() {
		return this.errors.size();
	}

	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}
}
