package com.mongol.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
