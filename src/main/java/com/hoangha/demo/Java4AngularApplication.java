package com.hoangha.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Java4AngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java4AngularApplication.class, args);
	}
}
