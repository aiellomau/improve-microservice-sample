package com.improve.reservations.reservation.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.improve.reservations.reservation.controller.data.CampsiteAvailabilities;
import com.improve.reservations.reservation.controller.data.ReservationInfo;
import com.improve.reservations.reservation.model.Reservation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "reservation")
public interface IReservationController {

	/**
	 * Provide information of the availability of the campsite for a given dates
	 * range
	 * 
	 * @param from
	 * @param to
	 * @return CampsiteAvailabilities instance for the given dates and campsite 1 by
	 *         default
	 */
	@ApiOperation(value = "Provide information of the availability of the campsite for a given dates range")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 500, message = "Error while parsing dates") })
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
	@ApiOperation(value = "Find by Reservation Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = Reservation.class),
			@ApiResponse(code = 404, message = "Rservation Id not found") })
	@GetMapping("/{reservationId}")
	Reservation findById(Long reservationId);

	/**
	 * Add new reservation.
	 * 
	 * @param reservation
	 * @return
	 */
	@PostMapping("/add")
	Reservation add(ReservationInfo reservation);

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
	 * @return
	 */
	@DeleteMapping("/cancel/{reservationId}")
	Reservation cancel(Long reservationId);

}
