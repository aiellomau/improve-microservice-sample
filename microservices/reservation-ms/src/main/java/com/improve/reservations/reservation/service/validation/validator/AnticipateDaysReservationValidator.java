package com.improve.reservations.reservation.service.validation.validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.AnticipateDaysOutException;
import com.improve.reservations.reservation.exception.ReservationValidationException;
import com.improve.reservations.reservation.service.util.DateUtils;

@Service
public class AnticipateDaysReservationValidator implements ReservationValidator {

	@Value("${validation.reservation.anticipate.days.error.msg}")
	private String message;

	@Override
	public void checkConstraint(final ReservationInfo reservation, final Campsite campsite)
			throws ReservationValidationException {

		final long diff = daysDiff(Date.from(Instant.now()), reservation.getArrivalDate());

		if (diff > campsite.getAnticipateMaxDaysReservation() || diff < campsite.getAnticipateMinDaysReservation()) {
			throw new AnticipateDaysOutException(getMessage());
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
