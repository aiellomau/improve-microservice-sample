package com.improve.reservations.reservation.controller.data;

import javax.validation.constraints.Email;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserInfo {

	private Long id;

	private String lastname;

	private String firstname;

	@Email
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
