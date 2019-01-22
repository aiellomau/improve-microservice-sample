package com.improve.reservations.reservation.service.validation.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationDaysException;
import com.improve.reservations.reservation.exception.ReservationValidationException;

@Service
public class DaysReservationValidator implements ReservationValidator {

	@Value("${validation.reservation.date.days.error.msg}")
	private String errorMessage;

	@Override
	public void checkConstraint(final ReservationInfo reservation, final Campsite campsite)
			throws ReservationValidationException {

		if (reservation.getArrivalDate().equals(reservation.getDepartureDate())
				|| reservation.getArrivalDate().after(reservation.getDepartureDate())) {
			throw new ReservationDaysException(getErrorMessage());
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
