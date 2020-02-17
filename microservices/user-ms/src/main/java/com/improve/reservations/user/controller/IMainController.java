package com.improve.reservations.user.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface IMainController {

	@GetMapping("/config/{propertyId}")
	String configById(String propertyId);

}
