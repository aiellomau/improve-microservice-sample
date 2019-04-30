package com.improve.reservations.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.repository.UserRepository;
import com.improve.reservations.user.service.stream.UserSource;

/**
 * Default User implementation for CRUD operations
 * 
 * @author maiello
 *
 */
@EnableBinding(UserSource.class)
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private UserSource userSource;

	@Override
	public User save(final User user) {
		User toSave = new User();
		toSave.setEmail(user.getEmail());
		toSave.setLastname(user.getLastname());
		toSave.setFirstname(user.getFirstname());
		User uSaved = userRepository.save(toSave);
		LOG.debug("- saved user -");
		this.userSource.userNewChannel().send(MessageBuilder.withPayload(uSaved).build());
		LOG.debug("- saved user msg sent -");
		return uSaved;
	}

	@Override
	public void delete(Long userId) {
		this.userRepository.deleteById(userId);
		this.userSource.userDeleteChannel().send(MessageBuilder.withPayload(userId).build());

	}

	@Override
	public User findById(final Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User [%s] not found", userId)));

	}

	@Override
	public List<User> findByLastname(final String lastname) {
		return userRepository.findByLastname(lastname);
	}

	@Autowired
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setUserSource(UserSource userSource) {
		this.userSource = userSource;
	}
}
