package com.improve.reservations.reservation.service.validation;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationValidationException;

public interface ReservationValidatorService {

	void validate(ReservationInfo reservation, Campsite campsite) throws ReservationValidationException;

}
