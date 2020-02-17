package com.improve.reservations.user;

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

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
public class UserWebIntegrationTest {

	@Autowired
	private UserService userService;

	@Value("${local.server.port}")
	private int serverPort;

	private RestTemplate restTemplate;

	private User userPeter;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
		userService.save(new User("Peter", "Tosk", "petertosk@mail.com"));
		userPeter = userService.findByLastname("Tosk").get(0);
	}

	@Test
	public void findByUserId_Ok() {

		final String url = userURL() + "user/" + userPeter.getId();
		final ResponseEntity<User> response = requestGet(User.class, MediaType.APPLICATION_JSON, url);

		assertThat(response.getBody(), equalTo(userPeter));
	}

	@Test
	public void findUserById_NotFound() {
		final String url = userURL() + "user/" + userPeter.getId();
		try {
			requestGet(User.class, MediaType.APPLICATION_JSON, url);
		} catch (final Exception e) {
			assertTrue(e instanceof HttpClientErrorException);
			final HttpClientErrorException httpError = (HttpClientErrorException) e;
			assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
		}
	}

	private String userURL() {
		return "http://localhost:" + serverPort + "/";
	}

	private <T> ResponseEntity<T> requestGet(final Class<T> value, final MediaType mediaType, final String url) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(mediaType));

		final HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		final ResponseEntity<T> resultEntity = restTemplate.exchange(url, HttpMethod.GET, entity, value);

		return resultEntity;
	}
}
