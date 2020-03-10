package com.improve.reservations.reservation.campsitestub;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.reservation.client.data.Campsite;

@RestController
@RequestMapping("/campsite")
@Profile("test")
public class CampsiteStub {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campsite> getById(@PathVariable("id") final long id) {
		if (id != 1) {
			return new ResponseEntity<Campsite>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Campsite>(new Campsite(1, "Campsite 1"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<Campsite> getAll() {
		return new PagedModel<Campsite>(Arrays.asList(new Campsite(1, "Campsite 1")), new PageMetadata(1, 0, 1));
	}

}
