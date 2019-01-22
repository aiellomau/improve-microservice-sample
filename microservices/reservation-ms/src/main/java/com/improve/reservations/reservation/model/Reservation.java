package com.improve.reservations.reservation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "RESERVATIONTABLE")
public class Reservation {

	@Id
	@GeneratedValue
	private long reservationId;

	private long userId;

	private long campsiteId;

	private Date arrivalDate;

	private Date departureDate;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	public Reservation() {
		super();
	}

	public Reservation(final long userId, final long campsiteId, final ReservationStatus status) {
		super();
		this.userId = userId;
		this.campsiteId = campsiteId;
		this.status = status;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public long getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(final long campsiteId) {
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
