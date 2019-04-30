package com.improve.reservations.campsite.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.improve.reservations.campsite.model.Campsite;

public interface ICampsiteController {

	@GetMapping("/{id}")
	Campsite findById(Long id);

	@PostMapping("/add")
	String newCampsite(Campsite campsite);

	@DeleteMapping("/delete/{campsiteId}")
	void deleteCampsite(Long campsiteId);

}
