package com.improve.reservations.reservation.stream.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.improve.reservations.reservation.client.data.User;

@EnableBinding(Sink.class)
public class UserListener {

	private static final Logger LOG = LoggerFactory.getLogger(UserListener.class);

	@StreamListener(target = Sink.INPUT)
	public void listenForNewUser(User user) {
		LOG.info(" received new user [" + user.toString() + "] ");
	}

}
