package com.shruti.ecom_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan(basePackages = "com.shruti")
@EnableJpaRepositories(basePackages = "com.shruti")
public class  EcomBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomBackApplication.class, args);
	}

}
