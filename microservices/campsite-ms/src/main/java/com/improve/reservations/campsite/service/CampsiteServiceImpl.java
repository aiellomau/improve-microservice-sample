package com.improve.reservations.campsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.repository.CampsiteRepository;

/**
 * Default Campsite implementation for CRUD operations
 * 
 * @author maiello
 *
 */
@Service
public class CampsiteServiceImpl implements CampsiteService {

	private CampsiteRepository campsiteRepository;

	@Override
	public void save(final Campsite campsite) {
		this.campsiteRepository.save(campsite);
	}

	@Override
	public Campsite findById(final Long campsiteId) {

		return campsiteRepository.findById(campsiteId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Campsite [%s] not found", campsiteId)));
	}

	@Autowired
	public void setCampsiteRepository(final CampsiteRepository campsiteRepository) {
		this.campsiteRepository = campsiteRepository;
	}
}
