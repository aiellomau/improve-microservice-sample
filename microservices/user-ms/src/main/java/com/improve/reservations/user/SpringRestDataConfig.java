package com.improve.reservations.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.improve.reservations.user.model.User;

@Configuration
class SpringRestDataConfig extends RepositoryRestConfigurerAdapter {

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.exposeIdsFor(User.class);
			}
		};
	}

}
