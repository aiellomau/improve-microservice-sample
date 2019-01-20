package com.improve.reservations.campsite.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.improve.reservations.campsite.model.Campsite;

public interface ICampsiteController {

	@GetMapping("/{id}")
	Campsite findById(Long id);

}
