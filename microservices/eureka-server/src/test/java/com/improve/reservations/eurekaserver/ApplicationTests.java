package com.improve.reservations.eurekaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@LocalServerPort
	private final int port = 0;
	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	public void lastnLoads() {
		final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/lastn/",
				String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void eurekaLoads() {
		final ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
