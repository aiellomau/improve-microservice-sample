package com.improve.reservations.reservation.controller.data;

import java.util.Date;

public class CampsiteAvailability {

	private Date date;

	private boolean available;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
