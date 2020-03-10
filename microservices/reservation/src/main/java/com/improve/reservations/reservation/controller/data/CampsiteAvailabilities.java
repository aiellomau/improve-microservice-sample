package com.improve.reservations.reservation.controller.data;

import java.util.ArrayList;
import java.util.List;

public class CampsiteAvailabilities {

	private List<CampsiteAvailability> dates = new ArrayList<>();

	public List<CampsiteAvailability> append(CampsiteAvailability date) {
		this.dates.add(date);
		return this.dates;
	}

	public List<CampsiteAvailability> getDates() {
		return dates;
	}

}
