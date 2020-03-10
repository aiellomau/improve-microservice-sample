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

	void delete(Long campsiteId);

	Campsite findById(Long campsiteId);

}
