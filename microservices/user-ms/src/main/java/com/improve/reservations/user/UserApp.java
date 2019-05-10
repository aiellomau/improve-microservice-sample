package com.improve.reservations.user;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class UserApp {

//	private final UserService userService;

//	@Autowired
//	public UserApp(final UserService userService) {
//		this.userService = userService;
//	}

	@PostConstruct
	public void generateTestData() {
//		userService.save(new User("Peter", "Tosk", "petertosk@mail.com"));
	}

	public static void main(final String[] args) {
		SpringApplication.run(UserApp.class, args);
	}

}
