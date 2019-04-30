package com.improve.reservations.campsite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.repository.CampsiteRepository;

/**
 * Default Campsite implementation for CRUD operations
 * 
 * @author maiello
 *
 */
@EnableBinding(CampsiteSource.class)
@Service
public class CampsiteServiceImpl implements CampsiteService {

	private static final Logger LOG = LoggerFactory.getLogger(CampsiteServiceImpl.class);

	private CampsiteRepository campsiteRepository;

	private CampsiteSource campsiteSourve;

	@Override
	public void save(final Campsite campsite) {
		// TODO try Exceptions services
		this.campsiteRepository.save(campsite);
		this.campsiteSourve.campsiteNewChannel().send(MessageBuilder.withPayload(campsite).build());
		LOG.debug("Message Nfor ew sent.");
	}

	@Override
	public void delete(final Long campsiteId) {
		// TODO try Exceptions services
		this.campsiteRepository.deleteById(campsiteId);
		this.campsiteSourve.campsiteDeleteChannel().send(MessageBuilder.withPayload(campsiteId).build());
		LOG.debug("Message for Delete sent.");
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

	@Autowired
	public void setCampsiteSourve(CampsiteSource campsiteSourve) {
		this.campsiteSourve = campsiteSourve;
	}
}
