package com.improve.reservations.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ReservationTestApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ReservationTestApp.class);
		app.setAdditionalProfiles("test");
		app.run(args);
	}

}
