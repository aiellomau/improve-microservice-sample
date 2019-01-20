package com.improve.reservations.reservation.service.validation.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.MaxDaysExcedeedException;
import com.improve.reservations.reservation.exception.ReservationValidationException;
import com.improve.reservations.reservation.service.util.DateUtils;

@Service
public class MaxDaysReservationValidator implements ReservationValidator {

	@Value("${validation.reservation.max.days.exceeded.msg}")
	private String message;

	@Override
	public void checkConstraint(final ReservationInfo reservation, final Campsite campsite)
			throws ReservationValidationException {

		if (daysDiff(reservation.getArrivalDate(), reservation.getDepartureDate()) > campsite
				.getMaxReservedDaysAllowed()) {
			throw new MaxDaysExcedeedException(getMessage());
		}
	}

	private long daysDiff(final Date arrivalDate, final Date departureDate) {

		final LocalDate dateTo = DateUtils.convert(arrivalDate);
		final LocalDate dateFrom = DateUtils.convert(departureDate);

		return ChronoUnit.DAYS.between(dateTo, dateFrom);
	}

	public String getMessage() {
		return message;
	}

}
