package com.improve.reservations.reservation.controller.data;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.improve.reservations.reservation.client.data.User;
import com.improve.reservations.reservation.controller.convert.JsonDateSerializer;
import com.improve.reservations.reservation.model.ReservationStatus;

public class ReservationInfo {

	private Long reservationId;

	private User user;

	private CampsiteInfo campsite;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date arrivalDate;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date departureDate;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(final Long reservationId) {
		this.reservationId = reservationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public CampsiteInfo getCampsite() {
		return campsite;
	}

	public void setCampsite(final CampsiteInfo campsite) {
		this.campsite = campsite;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(final Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(final ReservationStatus status) {
		this.status = status;
	}
}
