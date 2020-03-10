package com.improve.reservations.campsite;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.service.CampsiteService;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
@RefreshScope
public class CampsiteApp {

	private final Logger LOG = LoggerFactory.getLogger(CampsiteApp.class);

	private final CampsiteService campsiteService;

	@Autowired
	public CampsiteApp(final CampsiteService campsiteService) {
		this.campsiteService = campsiteService;
	}

	/**
	 * By default we will have only one Campsite named "Campsite 1" Generate
	 * Campsite by default.
	 */
	@PostConstruct
	public void generateTestData() {
		try {
			campsiteService.save(new Campsite("Campsite 1", 3, 30, 1));
		} catch (final Exception e) {
			LOG.error("Could not save default Campsite", e);
		}
	}

	public static void main(final String[] args) {
		SpringApplication.run(CampsiteApp.class, args);
	}

}
