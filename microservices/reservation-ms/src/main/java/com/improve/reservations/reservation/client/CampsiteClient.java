package com.improve.reservations.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.improve.reservations.reservation.client.data.Campsite;

@FeignClient("campsite")
public interface CampsiteClient {

	@GetMapping("/{id}")
	Campsite findById(@PathVariable("id") Long id);

}
