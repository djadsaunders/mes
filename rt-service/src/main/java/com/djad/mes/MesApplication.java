package com.djad.mes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@EnableSwagger2
public class MesApplication {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("MES")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.djad.mes"))
				.paths(any())
				.build()
				.apiInfo(new ApiInfo("MES Real-Time (Shopfloor) Service",
						"Write and read data from the real-time database", "1.0.0",
						"", new Contact("Dan Saunders","dan@madeupemail.com",null),
						null,null, Collections.emptyList()));
	}

	public static void main(String[] args) {
		SpringApplication.run(MesApplication.class, args);
	}
}
