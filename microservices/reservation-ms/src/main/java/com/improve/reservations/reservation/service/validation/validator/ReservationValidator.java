package com.improve.reservations.reservation.service.validation.validator;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationValidationException;

public interface ReservationValidator {

	void checkConstraint(ReservationInfo reservation, Campsite campsite) throws ReservationValidationException;

}
