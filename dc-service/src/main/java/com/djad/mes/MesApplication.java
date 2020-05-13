package com.djad.mes;

import static springfox.documentation.builders.PathSelectors.any;

import java.util.Collections;
import java.util.Properties;

import com.djad.mes.events.EventSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ImportResource("beans.xml")
public class MesApplication {

	private static final Logger logger = LoggerFactory.getLogger(MesApplication.class);

	@Value("#{'${event-sender}'}")
	private String eventSenderClass;

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.groupName("MES")
        	.select()
        	.apis(RequestHandlerSelectors.basePackage("com.djad.mes"))
        	.paths(any())
        	.build()
            .apiInfo(new ApiInfo("MES Data Capture Service", "Services to capture data, typically from automation", "1.0.0", "",
            	new Contact("Dan Saunders","dan@madeupemail.com",null),null,null,Collections.emptyList()));
    }

	public static void main(String[] args) {
		SpringApplication.run(MesApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady(ApplicationReadyEvent event) {
		EventSender eventSender = (EventSender)event.getApplicationContext().getBean("eventSender");
		Properties properties = eventSender.getProperties();

		logger.info("Using event sender: " + eventSender.getClass().getName());
		for (String property : properties.stringPropertyNames()) {
			logger.info("- " + property + "=" + properties.getProperty(property));
		}
	}
}