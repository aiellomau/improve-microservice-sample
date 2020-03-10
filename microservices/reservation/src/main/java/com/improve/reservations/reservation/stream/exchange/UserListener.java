package com.improve.reservations.reservation.stream.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.improve.reservations.reservation.client.data.User;

@EnableBinding(UserLink.class)
public class UserListener {

	private static final Logger LOG = LoggerFactory.getLogger(UserListener.class);

	@StreamListener(target = UserLink.CHANNEL_USER_NEW_NAME)
	public void listenForNewUser(User user) {
		LOG.info(" received new user [" + user.toString() + "] ");
	}

	@StreamListener(target = UserLink.CHANNEL_USER_DELETE_NAME)
	public void listenForDeleteUser(Long userId) {
		LOG.info(" received delete user [" + userId + "] ");
	}

}
