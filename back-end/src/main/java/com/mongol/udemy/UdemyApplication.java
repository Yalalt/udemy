package com.mongol.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongol.model.Admin;

@SpringBootApplication
@RestController
public class UdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("<h3>Hello %s! Welcome to Spring</h3>", name);
	}

	@GetMapping("/admin")
	public String addAdmin(@RequestParam String name, @RequestParam String email) {
		try {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setEmail(email);
			System.out.println("Username: " + admin.getName() + ", Email: " + admin.getEmail());
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}
}
