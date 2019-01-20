package com.improve.reservations.reservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReservationStatus {

	PENDING("pending"), RESERVED("reserved"), CANCELLED("cancelled");

	private String value;

	private ReservationStatus(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	/**
	 * Use this in place of valueOf.
	 *
	 * @param value real value
	 * @return ReservationStatus corresponding to the value
	 *
	 * @throws IllegalArgumentException If the specified value does not map to one
	 *                                  of the known values in this enum.
	 */
	public static ReservationStatus fromValue(final String value) {
		if (value == null || "".equals(value)) {
			throw new IllegalArgumentException("Value cannot be null or empty!");
		}
		for (final ReservationStatus enumEntry : ReservationStatus.values()) {
			if (enumEntry.toString().equals(value)) {
				return enumEntry;
			}
		}

		throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
	}

}
