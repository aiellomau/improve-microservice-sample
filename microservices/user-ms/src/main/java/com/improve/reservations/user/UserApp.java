package com.improve.reservations.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.improve.reservations.user.model.User;
import com.improve.reservations.user.repository.UserRepository;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class UserApp {

	private final UserRepository userRepository;

	@Autowired
	public UserApp(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void generateTestData() {
		userRepository.save(new User("Peter", "Tosk", "petertosk@mail.com"));
	}

	public static void main(final String[] args) {
		SpringApplication.run(UserApp.class, args);
	}

}
