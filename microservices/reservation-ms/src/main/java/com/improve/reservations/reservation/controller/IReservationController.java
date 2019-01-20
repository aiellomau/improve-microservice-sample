package com.improve.reservations.reservation.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.improve.reservations.reservation.controller.data.CampsiteAvailabilities;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.model.Reservation;

public interface IReservationController {

	/**
	 * Provide information of the availability of the campsite for a given date
	 * range
	 * 
	 * @param from
	 * @param to
	 * @return CampsiteAvailabilities instance for the given dates and campsite 1 by
	 *         default
	 */
	@GetMapping("/avail/{from}/{to}")
	CampsiteAvailabilities availableDates(String from, final String to);

	/**
	 * Provide information of the availability of the campsite for a given date
	 * range
	 * 
	 * @param campsiteId
	 * @param from
	 * @param to
	 * @return CampsiteAvailabilities instance for the given dates
	 */
	@GetMapping("/avail/{campsiteId}/{from}/{to}")
	CampsiteAvailabilities availableDates(Long campsiteId, Date from, Date to);

	/**
	 * Find by Reservation Id
	 * 
	 * @param reservationId
	 * @return
	 */
	@GetMapping("/{reservationId}")
	Reservation findById(Long reservationId);

	/**
	 * Add new reservation.
	 * 
	 * @param reservation
	 * @return
	 */
	@PostMapping("/add")
	Reservation save(ReservationInfo reservation);

	/**
	 * Update a reservation
	 * 
	 * @param reservation
	 * @return
	 */
	@PutMapping("/update")
	Reservation update(ReservationInfo reservation);

	/**
	 * Cancel a reservation
	 * 
	 * @param reservationId
	 */
	@DeleteMapping("/cancel/{reservationId}")
	void cancel(Long reservationId);

}
