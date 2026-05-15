package com.graniteexpo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GraniteexpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraniteexpoApplication.class, args);

	}

}
