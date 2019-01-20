package com.improve.reservations.reservation.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.improve.reservations.reservation.client.CampsiteClient;
import com.improve.reservations.reservation.client.UserClient;
import com.improve.reservations.reservation.client.data.Campsite;
import com.improve.reservations.reservation.client.data.User;
import com.improve.reservations.reservation.controller.data.CampsiteAvailabilities;
import com.improve.reservations.reservation.controller.data.CampsiteAvailability;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.exception.ReservationBadRequestException;
import com.improve.reservations.reservation.model.Reservation;
import com.improve.reservations.reservation.model.ReservationStatus;
import com.improve.reservations.reservation.repository.ReservationRepository;
import com.improve.reservations.reservation.service.util.DateUtils;
import com.improve.reservations.reservation.service.validation.ReservationValidatorService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOG = LoggerFactory.getLogger(ReservationServiceImpl.class);

	private ReservationRepository reservationRepository;
	private UserClient userClient;
	private CampsiteClient campsiteClient;
	private ReservationValidatorService reservationValidatorService;

	@Override
	public CampsiteAvailabilities availableDates(final Long campsiteId, final Date from, final Date to) {

		LOG.debug(String.format("Get availability for campsite %s from [%s] to [%s]", campsiteId, from, to));

		final List<Reservation> reserves = this.findBetweenDates(from, to, ReservationStatus.RESERVED, campsiteId);

		final List<LocalDate> rangeDates = DateUtils.getDatesBetween(from, to);

		final CampsiteAvailabilities availabilities = new CampsiteAvailabilities();
		rangeDates.stream().forEach(date -> {
			final CampsiteAvailability availableDate = new CampsiteAvailability();
			final boolean reserved = reserves.stream().filter(r -> r.getArrivalDate().before(DateUtils.convert(date))
					&& r.getDepartureDate().after(DateUtils.convert(date))).findAny().isPresent();

			availableDate.setAvailable(!reserved);
			availableDate.setDate(DateUtils.convert(date));
			availabilities.append(availableDate);
		});

		return availabilities;
	}

	@Override
	public Reservation save(final ReservationInfo reservationInfo) {

		LOG.debug(String.format("Reserving for campsite %s", reservationInfo.getCampsite().getId()));

		final Reservation reservation = new Reservation();
		reservation.setStatus(ReservationStatus.PENDING);

		Campsite campsite = getCampsite(reservationInfo);
		reservationValidatorService.validate(reservationInfo, campsite);

		// if all is ok -> reserving

		// Save user reservation data
		User uInfo = userClient.save(reservationInfo.getUser());

		reservation.setStatus(ReservationStatus.RESERVED);
		reservation.setArrivalDate(reservationInfo.getArrivalDate());
		reservation.setDepartureDate(reservationInfo.getDepartureDate());
		reservation.setCampsiteId(reservationInfo.getCampsite().getId());
		reservation.setUserId(uInfo.getUserId());
		// do reserve
		return reservationRepository.save(reservation);
	}

	/**
	 * Get an instance of Campsite from a given reservation
	 * 
	 * @param reservationInfo
	 * @return
	 */
	private Campsite getCampsite(final ReservationInfo reservationInfo) {
		try {
			return campsiteClient.findById(reservationInfo.getCampsite().getId());
		} catch (final HttpClientErrorException e) {
			if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
				throw new ReservationBadRequestException("Campsite does not exist!");
			}
			LOG.error("Unknown error while getting campsite info.", e);
			throw e;
		}
	}

	@Override
	public Reservation cancel(final Long reservationId) {

		final Reservation reservation = this.findById(reservationId);

		reservation.setStatus(ReservationStatus.CANCELLED);

		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation update(final ReservationInfo reservationInfo) {

		if (reservationInfo.getReservationId() == null) {
			throw new ReservationBadRequestException(String.format("Reservation Id missing"));
		}

		final Reservation reservation = this.findById(reservationInfo.getReservationId());

		reservationValidatorService.validate(reservationInfo, getCampsite(reservationInfo));

		reservation.setStatus(reservationInfo.getStatus());
		reservation.setArrivalDate(reservationInfo.getArrivalDate());
		reservation.setDepartureDate(reservationInfo.getDepartureDate());

		return reservationRepository.save(reservation);

	}

	@Override
	public Reservation findById(final Long reservationId) {
		return reservationRepository.findById(reservationId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Reservation [%s] not found", reservationId)));
	}

	@Override
	public List<Reservation> findBetweenDates(final Date from, final Date to) {
		return reservationRepository.findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqual(from, to);
	}

	@Override
	public List<Reservation> findBetweenDates(final Date from, final Date to, final ReservationStatus status) {
		return reservationRepository.findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqualAndStatus(from, to,
				status);
	}

	@Override
	public List<Reservation> findBetweenDates(final Date from, final Date to, final ReservationStatus status,
			final Long campsiteId) {
		return reservationRepository
				.findByArrivalDateGreaterThanEqualAndDepartureDateLessThanEqualAndStatusAndCampsiteId(from, to, status,
						campsiteId);
	}

	@Autowired
	public void setReservationRepository(final ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Autowired
	public void setCampsiteClient(final CampsiteClient campsiteClient) {
		this.campsiteClient = campsiteClient;
	}

	@Autowired
	public void setUserClient(final UserClient userClient) {
		this.userClient = userClient;
	}

	@Autowired
	public void setReservationValidatorService(final ReservationValidatorService reservationValidatorService) {
		this.reservationValidatorService = reservationValidatorService;
	}

}
