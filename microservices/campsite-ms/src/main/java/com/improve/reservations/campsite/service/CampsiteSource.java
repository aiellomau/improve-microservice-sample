package com.improve.reservations.campsite.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CampsiteSource {

	@Output("campsiteNewChannel")
	MessageChannel campsiteNewChannel();
	
	@Output("campsiteDeleteChannel")
	MessageChannel campsiteDeleteChannel();

}
