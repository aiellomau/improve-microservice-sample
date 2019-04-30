package com.improve.reservations.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.service.UserService;

@RestController
public class UserController implements IUserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	

	@Override
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.delete(userId);
		LOG.info(userId + "Deleted!");
	}

	@Override
	public User findById(@PathVariable final Long id) {

		return userService.findById(id);
	}

	@Override
	public User save(@RequestBody final User user) {
		LOG.info(user.toString() + "Saving");
		return userService.save(user);
	}
	

	@Autowired
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
}
