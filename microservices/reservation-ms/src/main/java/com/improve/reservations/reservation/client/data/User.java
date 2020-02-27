package com.improve.reservations.reservation.client.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User extends RepresentationModel<User> {

	private String lastname;

	private String firstname;

	private String email;

	@JsonProperty("id")
	private long userId;

	public User() {
	}

	public User(long id, String firstname, String lastname, String email) {
		super();
		this.userId = id;
		this.firstname = firstname;
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserrId(long id) {
		this.userId = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
