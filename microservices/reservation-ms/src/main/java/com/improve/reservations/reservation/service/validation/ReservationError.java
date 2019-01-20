package com.improve.reservations.reservation.service.validation;

public class ReservationError {

	private String code;
	private String message;

	public ReservationError() {
	}

	public ReservationError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
