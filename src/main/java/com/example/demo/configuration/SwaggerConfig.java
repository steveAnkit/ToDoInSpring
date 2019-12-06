package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String CONTROLLER_BASE_PATH = "com.example.demo.controller";
	
	@Bean
	public Docket authorApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(CONTROLLER_BASE_PATH))
				.paths(PathSelectors.any())
				.build();
				
				
	}
	
	
	// Un-comment for the additional meta info 
	
	/*
	 * private ApiInfo metaInfo() {
	 * 
	 * ApiInfo apiInfo = new ApiInfo( "TO Spring Boot Application",
	 * "this application is for managing the tasks", "1.0", "Terms of Service", new
	 * Contact("Ankit Sharma", "https://www.linkedin.com/in/ankit-sharma-90446755/",
	 * "ankitsharma1702@gmail.com"), "Apache License Version 2.0",
	 * "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>());
	 * return apiInfo;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	

}
