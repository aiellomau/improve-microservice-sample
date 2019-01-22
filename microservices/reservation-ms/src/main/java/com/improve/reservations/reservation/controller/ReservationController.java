package com.improve.reservations.reservation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.improve.reservations.reservation.controller.data.CampsiteAvailabilities;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.AnticipateDaysOutException;
import com.improve.reservations.reservation.exception.MaxDaysExcedeedException;
import com.improve.reservations.reservation.exception.ReservationBadRequestException;
import com.improve.reservations.reservation.exception.ReservationDaysException;
import com.improve.reservations.reservation.exception.UnAvailabilityException;
import com.improve.reservations.reservation.model.Reservation;
import com.improve.reservations.reservation.service.ReservationService;

@RestController
public class ReservationController implements IReservationController {

	private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private ReservationService reservationService;

	@Override
	public CampsiteAvailabilities availableDates(@PathVariable final String from, @PathVariable final String to) {

		try {
			return reservationService.availableDates(1l, dateFormat.parse(from), dateFormat.parse(to));
		} catch (final ParseException e) {
			LOG.error("Could not parse dates", e);
			throw new IllegalArgumentException(e);
		}

	}

	@Override
	public CampsiteAvailabilities availableDates(@PathVariable final Long campsiteId, @PathVariable final Date from,
			@PathVariable final Date to) {

		return reservationService.availableDates(campsiteId, from, to);
	}

	@Override
	public Reservation findById(@PathVariable final Long reservationId) {

		try {
			return reservationService.findById(reservationId);
		} catch (final ReservationBadRequestException mrEx) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mrEx.getLocalizedMessage(), mrEx);
		}

	}

	@Override
	public Reservation add(@RequestBody final ReservationInfo reservation) {

		try {
			return reservationService.save(reservation);
		} catch (ReservationDaysException | AnticipateDaysOutException | MaxDaysExcedeedException
				| ReservationBadRequestException mrEx) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mrEx.getLocalizedMessage(), mrEx);
		} catch (final UnAvailabilityException uaEx) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, uaEx.getLocalizedMessage(), uaEx);
		}
	}

	@Override
	public Reservation update(@RequestBody final ReservationInfo reservation) {

		try {
			return reservationService.update(reservation);
		} catch (ReservationDaysException | AnticipateDaysOutException | MaxDaysExcedeedException
				| ReservationBadRequestException mrEx) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mrEx.getLocalizedMessage(), mrEx);
		} catch (final UnAvailabilityException uaEx) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, uaEx.getLocalizedMessage(), uaEx);
		}
	}

	@Override
	public Reservation cancel(@PathVariable final Long reservationId) {
		try {
			return reservationService.cancel(reservationId);
		} catch (final ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), ex);
		}
	}

	@Autowired
	public void setReservationService(final ReservationService reservationService) {
		this.reservationService = reservationService;
	}

}
