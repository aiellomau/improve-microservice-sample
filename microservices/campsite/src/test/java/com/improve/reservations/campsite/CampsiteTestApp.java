package com.improve.reservations.campsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CampsiteTestApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CampsiteTestApp.class);
		app.setAdditionalProfiles("test");
		app.run(args);
	}

}
