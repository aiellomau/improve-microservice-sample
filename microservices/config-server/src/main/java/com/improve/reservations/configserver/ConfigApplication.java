package com.improve.reservations.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Expose following REST endpoints: <br />
 * /{application}/{profile}[/{label}] <br />
 * /{application}-{profile}.yml<br />
 * /{label}/{application}-{profile}.yml <br />
 * /{application}-{profile}.properties <br />
 * /{label}/{application}-{profile}.properties
 * 
 * @author maiello
 *
 */

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
