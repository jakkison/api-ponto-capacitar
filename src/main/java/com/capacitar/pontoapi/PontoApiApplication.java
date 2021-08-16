package com.capacitar.pontoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = {"com.capacitar.pontoapi.model"})
@ComponentScan(basePackages = {"com.*"})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
public class PontoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);
	}

}
