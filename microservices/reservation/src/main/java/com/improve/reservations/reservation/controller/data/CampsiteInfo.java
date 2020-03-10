package com.improve.reservations.reservation.controller.data;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CampsiteInfo {

	private Long id;

	private String name;

	private int maxReservedDaysAllowed;

	private int beforeMaxDaysForReservation;

	private int beforeMinDaysForReservation;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getBeforeMaxDaysForReservation() {
		return beforeMaxDaysForReservation;
	}

	public void setBeforeMaxDaysForReservation(final int beforeMaxDaysForReservation) {
		this.beforeMaxDaysForReservation = beforeMaxDaysForReservation;
	}

	public int getBeforeMinDaysForReservation() {
		return beforeMinDaysForReservation;
	}

	public void setBeforeMinDaysForReservation(final int beforeMinDaysForReservation) {
		this.beforeMinDaysForReservation = beforeMinDaysForReservation;
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

}
