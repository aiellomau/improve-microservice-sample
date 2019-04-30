package com.improve.reservations.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.improve.reservations.user.model.User;

public interface IUserController {

	@GetMapping("/{id}")
	User findById(Long id);

	@PostMapping("/add")
	User save(User user);

	@DeleteMapping("/delete/{userId}")
	void deleteUser(Long userId);

}
