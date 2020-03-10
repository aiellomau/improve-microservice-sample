package com.improve.reservations.user.service.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserSource {

	@Output("userNewChannel")
	MessageChannel userNewChannel();

	@Output("userDeleteChannel")
	MessageChannel userDeleteChannel();

}
