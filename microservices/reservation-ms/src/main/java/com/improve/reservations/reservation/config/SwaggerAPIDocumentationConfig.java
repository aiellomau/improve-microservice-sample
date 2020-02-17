/**
 * @author maiello
 *
 *         Create on Aug 26, 2019
 */
package com.improve.reservations.reservation.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 */
@EnableSwagger2
@Configuration
public class SwaggerAPIDocumentationConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Value("${spring.application.title}")
	private String title;

	@Value("${spring.profiles.active}")
	private String profile;

	ApiInfo apiInfo() {

		// @formatter:off
		return new ApiInfoBuilder().title(title).description("Reservation REST API")
				.contact(new Contact("Mauricio Aiello", "https://github.com/aiellomau", "mauricio.aiello@gmail.com"))
				.build();
		// @formatter:on
	}

	@Bean
	public Docket configureControllerPackageAndConvertors() {

		final List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(jwtHeaderParameter().build());

		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build()
				.globalOperationParameters(parameters).directModelSubstitute(LocalDate.class, java.util.Date.class)
				.apiInfo(apiInfo());
		// @formatter:on
	}

	private ParameterBuilder jwtHeaderParameter() {

		// @formatter:off
		final ParameterBuilder aParamBuilder = new ParameterBuilder();
		aParamBuilder.name(AUTHORIZATION_HEADER).description("JWT token").defaultValue("Bearer <Paste Here>")
				.modelRef(new ModelRef("string")).parameterType("header")
				// .required(apiSecure.getInsecure() != null ? !apiSecure.getInsecure() :
				// Boolean.TRUE)
				.build();
		// @formatter:on
		return aParamBuilder;
	}

}
