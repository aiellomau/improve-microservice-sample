package com.improve.reservations.campsite;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.improve.reservations.campsite.model.Campsite;
import com.improve.reservations.campsite.service.CampsiteService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CampsiteApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CampsiteWebIntegrationTest {

	@Autowired
	private CampsiteService campsiteService;

	@Value("${local.server.port}")
	private int serverPort;

	private Campsite campsite;

	private RestTemplate restTemplate;

	@Before
	public void setup() {
		campsite = campsiteService.findById(1l);
		restTemplate = new RestTemplate();
	}

	private String campsiteURL() {
		return "http://localhost:" + serverPort;
	}

	@Test
	public void findCampsiteById_Ok() {
		final String url = campsiteURL() + "/campsite/" + campsite.getId();
		final ResponseEntity<Campsite> response = requestGet(Campsite.class, MediaType.APPLICATION_JSON, url);

		assertThat(response.getBody(), equalTo(campsite));
	}

	@Test
	public void findCampsiteById_NotFound() {
		final String url = campsiteURL() + "/campsite/99999";
		try {
			requestGet(Campsite.class, MediaType.APPLICATION_JSON, url);
		} catch (final Exception e) {
			assertTrue(e instanceof HttpClientErrorException);
			final HttpClientErrorException httpError = (HttpClientErrorException) e;
			assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
		}
	}

	private <T> ResponseEntity<T> requestGet(final Class<T> value, final MediaType mediaType, final String url) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(mediaType));

		final HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		final ResponseEntity<T> resultEntity = restTemplate.exchange(url, HttpMethod.GET, entity, value);

		return resultEntity;
	}

}
