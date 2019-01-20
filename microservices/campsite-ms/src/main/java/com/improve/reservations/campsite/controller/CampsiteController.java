package com.improve.reservations.campsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.service.CampsiteService;

@RestController
public class CampsiteController implements ICampsiteController {

	private CampsiteService campsiteService;

	@Autowired
	public void setCampsiteService(final CampsiteService campsiteService) {
		this.campsiteService = campsiteService;
	}

	@Override
	public Campsite findById(@PathVariable final Long id) {
		return campsiteService.findById(id);
	}
}
