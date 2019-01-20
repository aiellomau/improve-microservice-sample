package com.improve.reservations.reservation.userstub;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.reservation.client.data.User;

@RestController
@RequestMapping("/user")
@Profile("test")
public class UserStub {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable("id") long id) {

		if (id != 42) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(new User(42, "Eberhard", "Wolff", "eberhard.wolff@gmail.com"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public PagedResources<User> getAll() {
		return new PagedResources<User>(Arrays.asList(new User(42, "Eberhard", "Wolff", "eberhard.wolff@gmail.com")),
				new PageMetadata(1, 0, 1));
	}

}
