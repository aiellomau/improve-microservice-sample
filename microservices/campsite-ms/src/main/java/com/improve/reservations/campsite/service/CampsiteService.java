package com.improve.reservations.campsite.service;

import com.improve.reservations.campsite.model.Campsite;

/**
 * Campsite Interface for CRUD operations over Campsite model
 * 
 * @author maiello
 *
 */
public interface CampsiteService {

	void save(Campsite campsite);

	Campsite findById(Long campsiteId);

}
