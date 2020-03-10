package com.improve.reservations.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.improve.reservations.reservation.client.data.User;

@FeignClient("user")
public interface UserClient {

	@GetMapping("/{id}")
	User findById(@PathVariable("id") Long id);

	@PostMapping("/add")
	User save(@RequestBody User user);
}
