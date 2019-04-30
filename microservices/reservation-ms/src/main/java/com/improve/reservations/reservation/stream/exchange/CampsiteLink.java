package com.improve.reservations.reservation.stream.exchange;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CampsiteLink {

	String CHANNEL_CAMP_NEW_NAME = "campsiteNewChannel";

	String CHANNEL_CAMP_DELETE_NAME = "campsiteDeleteChannel";

	@Input(value = CHANNEL_CAMP_NEW_NAME)
	SubscribableChannel campsiteNewChannel();

	@Input(value = CHANNEL_CAMP_DELETE_NAME)
	SubscribableChannel campsiteDeleteChannel();
}
