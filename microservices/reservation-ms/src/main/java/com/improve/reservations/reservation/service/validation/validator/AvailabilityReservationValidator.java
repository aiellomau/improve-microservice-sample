package com.improve.reservations.reservation.service.validation.validator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationValidationException;
import com.improve.reservations.reservation.exception.UnAvailabilityException;
import com.improve.reservations.reservation.model.Reservation;
import com.improve.reservations.reservation.model.ReservationStatus;
import com.improve.reservations.reservation.repository.ReservationRepository;

@Service
public class AvailabilityReservationValidator implements ReservationValidator {

	@Value("${validation.reservation.availability.msg}")
	private String message;

	private ReservationRepository reservationRepository;

	@Override
	public void checkConstraint(final ReservationInfo reservation, final Campsite campsite)
			throws ReservationValidationException {

		final List<Reservation> reserves = reservationRepository
				.findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqual(reservation.getArrivalDate(),
						reservation.getDepartureDate());

		if (!reserves.isEmpty() && reserves.size() > 0) {
			final Optional<Reservation> reserv = reserves.stream()
					.filter(r -> r.getStatus().equals(ReservationStatus.RESERVED)).findAny();
			if (reserv.isPresent()) {
				throw new UnAvailabilityException(getMessage());
			}
		}

	}

	public String getMessage() {
		return message;
	}

	@Autowired
	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

}
