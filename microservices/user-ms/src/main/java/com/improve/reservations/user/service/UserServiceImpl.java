package com.improve.reservations.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.repository.UserRepository;

/**
 * Default User implementation for CRUD operations
 * 
 * @author maiello
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public User save(final User user) {
		User toSave = new User();
		toSave.setEmail(user.getEmail());
		toSave.setLastname(user.getLastname());
		toSave.setFirstname(user.getFirstname());
		return userRepository.save(toSave);
	}

	@Override
	public User findById(final Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User %s not found", userId)));

	}

	@Override
	public List<User> findByLastname(final String lastname) {
		return userRepository.findByLastname(lastname);
	}

	@Autowired
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
