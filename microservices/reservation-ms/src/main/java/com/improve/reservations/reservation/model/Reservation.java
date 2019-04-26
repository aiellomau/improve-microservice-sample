package com.improve.reservations.reservation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "reservation", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "arrivalDate", "departureDate" }, name = "UniqueDatesValidation") })
public class Reservation {

	@Id
	@GeneratedValue
	private Long reservationId;

	private Long userId;

	private Long campsiteId;

	private Date arrivalDate;

	private Date departureDate;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	public Reservation() {
		super();
	}

	public Reservation(final Long userId, final Long campsiteId, final ReservationStatus status) {
		super();
		this.userId = userId;
		this.campsiteId = campsiteId;
		this.status = status;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	public Long getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(final Long campsiteId) {
		this.campsiteId = campsiteId;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);

	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
