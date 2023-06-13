package com.mongol.udemy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongol.model.*;
import com.mongol.repo.*;

import schema.AdminSchema;
import schema.LoginSchema;
import schema.ResponseSchema;
import schema.UserSchema;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MainController {
    @Autowired
    private AdminRepository adminRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private LessonRepository lessonRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PurchaseRepository purchaseRepository;

    @GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("<h3>Hello %s! Welcome to Spring</h3>", name);
	}

	@GetMapping("/admin")
	public String getAdmin() {
		try {
			
			
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	@PostMapping("/admin")
	public ResponseSchema addAdmin(@RequestBody AdminSchema adminSchema) {
		try {
			Admin admin = new Admin();
			admin.setName(adminSchema.getName());
			admin.setEmail(adminSchema.getEmail());
			admin.setPass(adminSchema.getPass());
			adminRepo.insert(admin);
			return ResponseSchema.getInstance(true);
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@GetMapping("/course")
	public String getCourse() {
		try {
			// Course course = new Course();
			// course.setName("Course name");
			// courseRepo.insert(course);
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	@GetMapping("/lesson")
	public String getLesson() {
		try {
			// Lesson lesson = new Lesson();
			// lesson.setName("Lesson name");
			// lessonRepo.insert(lesson);
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	@GetMapping("/user")
	public String getUser() {
		try {
			
			return String.format("User Data Receive");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	@PostMapping("/user")
	public ResponseSchema addUser(@RequestBody UserSchema userSchema) {
		try {
			User user = new User();
			user.setName(userSchema.getName());
			user.setEmail(userSchema.getEmail());
			user.setPass(userSchema.getPass());
			userRepo.insert(user);
			return ResponseSchema.getInstance(true);
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}

	@PostMapping("/userlogin")
	public ResponseSchema loginUser(@RequestBody LoginSchema loginSchema) {
		try {
			List<User> userList = new ArrayList<>();

			userList = userRepo.findAll();
			for(User user : userList) {
				if(loginSchema.getEmail().equals(user.getEmail())) {
					if(loginSchema.getPass().equals(user.getPass())) {
						return ResponseSchema.getInstance(true);
					}
				}
			}
			throw new Exception("Цахим шуудан эсвэл нууц үг таарахгүй байна");
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@GetMapping("/purchase")
	public String getPurchase() {
		try {
			// Purchase purchase = new Purchase();
			// purchase.setCourseid(1);
			// purchase.setUserid(1);
			// purchaseRepository.insert(purchase);
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}
}
