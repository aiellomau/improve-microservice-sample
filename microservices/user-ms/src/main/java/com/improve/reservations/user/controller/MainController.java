package com.improve.reservations.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MainController implements IMainController {

	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@Value("${encrypted.property:empty}")
	private String encryptedKey;

	@Override
	public String configById(@PathVariable("propertyId") String propertyId) {
		
		LOG.debug("Getting property: " + propertyId);
		
		switch (propertyId) {
		case "encrypted.property":
			return encryptedKey;

		default:
			break;
		}
		return null;
	}
}
