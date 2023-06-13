package com.mongol.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongol.repo.AdminRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = AdminRepository.class)
public class UdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyApplication.class, args);
	}
}
