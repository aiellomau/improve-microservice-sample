package com.improve.reservations.reservation.service.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationValidationException;
import com.improve.reservations.reservation.service.validation.validator.ReservationValidator;

@Service
public class ReservationValidatorServiceImpl implements ReservationValidatorService {

	private List<ReservationValidator> validators;

	@Override
	public void validate(final ReservationInfo reservation, final Campsite campsite)
			throws ReservationValidationException {

		validators.parallelStream().forEach(val -> val.checkConstraint(reservation, campsite));

	}

	@Autowired
	public void setValidators(final List<ReservationValidator> validators) {

		this.validators = validators;
	}
}
