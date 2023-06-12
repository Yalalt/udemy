package com.mongol.udemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongol.model.Admin;
import com.mongol.model.AdminRepository;

@RestController
public class MainController {
    @Autowired
    private AdminRepository adminRepo;


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
			adminRepo.insert(admin);
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}
}
