package com.improve.reservations.reservation.stream.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.improve.reservations.reservation.client.data.Campsite;

@EnableBinding(CampsiteLink.class)
public class CampsiteListener {

	private static final Logger LOG = LoggerFactory.getLogger(CampsiteListener.class);

	@StreamListener(target = CampsiteLink.CHANNEL_CAMP_NEW_NAME)
	public void listenForNewCampsite(Campsite campsite) {
		LOG.info(" received new campsite [" + campsite.toString() + "] ");
	}

	@StreamListener(target = CampsiteLink.CHANNEL_CAMP_DELETE_NAME)
	public void listenForDeleteCampsite(Long campsiteId) {
		LOG.info(" received delete campsite [" + campsiteId + "] ");
	}

}
