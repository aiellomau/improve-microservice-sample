package com.improve.reservations.reservation.client.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Campsite extends ResourceSupport {

	private String name;

	@JsonProperty("id")
	private long campsiteId;

	private int maxReservedDaysAllowed;

	private int anticipateMaxDaysReservation;

	private int anticipateMinDaysReservation;

	public Campsite() {
		super();
	}

	public Campsite(final long id, final String name) {
		super();
		this.campsiteId = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(final long id) {
		this.campsiteId = id;
	}

	public int getAnticipateMaxDaysReservation() {
		return anticipateMaxDaysReservation;
	}

	public void setAnticipateMaxDaysReservation(final int anticipateMaxDaysReservation) {
		this.anticipateMaxDaysReservation = anticipateMaxDaysReservation;
	}

	public int getAnticipateMinDaysReservation() {
		return anticipateMinDaysReservation;
	}

	public void setAnticipateMinDaysReservation(final int anticipateMinDaysReservation) {
		this.anticipateMinDaysReservation = anticipateMinDaysReservation;
	}

	public int getMaxReservedDaysAllowed() {
		return maxReservedDaysAllowed;
	}

	public void setMaxReservedDaysAllowed(final int maxReservedDaysAllowed) {
		this.maxReservedDaysAllowed = maxReservedDaysAllowed;
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
