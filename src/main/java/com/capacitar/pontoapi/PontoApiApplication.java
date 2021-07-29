package com.capacitar.pontoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
@EntityScan(basePackages = {"com.capacitar.pontoapi.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.capacitar.pontoapi.repository"})
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class PontoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);
	}

}
