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
import com.improve.reservations.campsite.service.stream.CampsiteSource;

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

	private CampsiteSource campsiteSource;

	@Override
	public void save(final Campsite campsite) {
		// TODO try Exceptions services
		this.campsiteRepository.save(campsite);
		LOG.debug("- saved campsite -");
		this.campsiteSource.campsiteNewChannel().send(MessageBuilder.withPayload(campsite).build());
		LOG.debug("- saved campsite msg sent -");
	}

	@Override
	public void delete(final Long campsiteId) {
		// TODO try Exceptions services
		this.campsiteRepository.deleteById(campsiteId);
		LOG.debug("- deleted campsite -");
		this.campsiteSource.campsiteDeleteChannel().send(MessageBuilder.withPayload(campsiteId).build());
		LOG.debug("- deleted campsite msg sent -");
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
	public void setCampsiteSource(CampsiteSource campsiteSource) {
		this.campsiteSource = campsiteSource;
	}
}
