package com.example.retailstore.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI usersMicroserviceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Retail Store Application").description("The Retail Store Billing System is a Java-based application that calculates the net payable amount for a given bill").version("1.0"));
	}
}