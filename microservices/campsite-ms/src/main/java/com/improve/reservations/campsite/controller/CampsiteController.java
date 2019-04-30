package com.improve.reservations.campsite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.service.CampsiteService;

@RestController
public class CampsiteController implements ICampsiteController {
	private static final Logger LOG = LoggerFactory.getLogger(CampsiteController.class);

	private CampsiteService campsiteService;

	@Override
	public Campsite findById(@PathVariable final Long id) {
		return campsiteService.findById(id);
	}

	@Override
	public String newCampsite(@RequestBody Campsite campsite) {
		this.campsiteService.save(campsite);
		LOG.info(campsite.toString() + "Saved!");
		return "campsite_new";
	}

	@Override
	public void deleteCampsite(@PathVariable("campsiteId") Long campsiteId) {
		this.campsiteService.delete(campsiteId);
		LOG.info(campsiteId + "Deleted!");
	}

	@Autowired
	public void setCampsiteService(final CampsiteService campsiteService) {
		this.campsiteService = campsiteService;
	}

}
