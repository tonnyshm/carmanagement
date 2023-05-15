package com.CarManagement.CarMan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class CarManApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarManApplication.class, args);
	}

}
