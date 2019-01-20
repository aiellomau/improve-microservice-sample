package com.improve.reservations.campsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Campsite {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Min(1)
	private int maxReservedDaysAllowed;

	@Column(nullable = false)
	@Min(1)
	private int anticipateMaxDaysReservation;

	@Column(nullable = false)
	@Min(1)
	private int anticipateMinDaysReservation;

	public Campsite() {
		super();
		id = 0l;
	}

	public Campsite(final String name, final int maxReservedDaysAllowed, final int anticipateMaxDaysReservation,
			final int anticipateMinDaysReservation) {
		super();
		this.name = name;
		this.maxReservedDaysAllowed = maxReservedDaysAllowed;
		this.anticipateMaxDaysReservation = anticipateMaxDaysReservation;
		this.anticipateMinDaysReservation = anticipateMinDaysReservation;
	}

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
