package com.cognizant.pensionerdetail;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 
 * @author Neelima, Ramya, Aniketh, Satya
 * Main class for Pensioner Detail MicroService
 * Annotated with @SpringBootApplication, @ComponentScan to scan all base packages
 *
 */
@SpringBootApplication
@EnableSwagger2
public class PensionerDetailsApplication {

	/**
	 * Main function to run the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PensionerDetailsApplication.class, args);
	}
	
	/**
	 * Bean for Swagger Configuration
	 * 
	 * @return - Swagger Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.pensionerdetail")).build().apiInfo(apiDetails());

	}

	/**
	 * 
	 * @return Api information
	 */
	private ApiInfo apiDetails() {

		return new ApiInfo("Pensioner Detail Service", "Microservice From Pension Management Project", "1.0",
				"Free To Use", new springfox.documentation.service.Contact("Admin", "", "admin@cognizant.com"),
				"API Licesence", "....", Collections.emptyList());
	}

}
