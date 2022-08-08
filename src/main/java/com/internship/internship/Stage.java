package com.internship.internship;

import com.internship.internship.entity.AuditAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class Stage {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditAware();
	}

	public static void main(String[] args) {
		SpringApplication.run(Stage.class, args);
	}

}
