package com.improve.reservations.user.service;

import java.util.List;

import com.improve.reservations.user.model.User;

/**
 * User Interface for CRUD operations over User model
 * 
 * @author maiello
 *
 */
public interface UserService {

	User save(User user);

	void delete(Long userId);

	User findById(Long userId);

	List<User> findByLastname(String lastname);
}
