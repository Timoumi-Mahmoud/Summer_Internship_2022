package com.internship.internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Stage {



	public static void main(String[] args) {
		SpringApplication.run(Stage.class, args);
	}

}
