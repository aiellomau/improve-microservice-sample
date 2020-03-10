package com.improve.reservations.reservation.stream.exchange;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserLink {

	String CHANNEL_USER_NEW_NAME = "userNewChannel";

	String CHANNEL_USER_DELETE_NAME = "userDeleteChannel";

	@Input(value = CHANNEL_USER_NEW_NAME)
	SubscribableChannel userNewChannel();

	@Input(value = CHANNEL_USER_DELETE_NAME)
	SubscribableChannel userDeleteChannel();
}
