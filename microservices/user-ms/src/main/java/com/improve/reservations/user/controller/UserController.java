package com.improve.reservations.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.service.UserService;

@RestController
public class UserController implements IUserController {

	private UserService userService;

	@Autowired
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public User findById(@PathVariable final Long id) {

		return userService.findById(id);
	}

	@Override
	public User save(@RequestBody final User user) {

		return userService.save(user);
	}
}
