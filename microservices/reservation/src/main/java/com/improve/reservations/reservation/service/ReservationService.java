package com.improve.reservations.reservation.service;

import java.util.Date;
import java.util.List;

import com.improve.reservations.reservation.controller.data.CampsiteAvailabilities;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.model.Reservation;
import com.improve.reservations.reservation.model.ReservationStatus;

public interface ReservationService {

	Reservation save(ReservationInfo reservation);

	Reservation update(ReservationInfo reservation);

	Reservation cancel(Long reservationId);

	Reservation findById(Long reservationId);

	List<Reservation> findBetweenDates(Date from, Date to);

	List<Reservation> findBetweenDates(Date from, Date to, ReservationStatus status);

	List<Reservation> findBetweenDates(Date from, Date to, ReservationStatus status, Long campsiteId);

	CampsiteAvailabilities availableDates(Long campsiteId, Date from, Date to);

}
