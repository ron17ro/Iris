package com.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


//@EnableAutoConfiguration
@SpringBootApplication
public class HelpdeskApplication extends SpringBootServletInitializer{
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(HelpdeskApplication.class);
	    }

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}
}
