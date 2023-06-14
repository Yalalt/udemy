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
import schema.CourseSchema;
import schema.LessonSchema;
import schema.LoginResponse;
import schema.LoginSchema;
import schema.ResponseSchema;
import schema.UserSchema;
import util.PasswordUtils;

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
	public ResponseSchema getAdmin() {
		try {
			return ResponseSchema.getInstance(true, "admin name");
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
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
			return String.format("Successfull");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	@PostMapping("/course")
	public ResponseSchema addCourse(@RequestBody CourseSchema courseSchema) {
		try {
			if (courseSchema.getName() == null)
				throw new Exception("Заавал сургалтын нэр оруулах шаардлагатай");
			else if (courseSchema.getImgUrl() == null || courseSchema.getPrice() == null
					|| courseSchema.getRealPrice() == null)
				throw new Exception("Заавал мэдээллээ бүрэн оруулах шаардлагатай");

			Course course = new Course();
			course.setName(courseSchema.getName());
			course.setImgUrl(courseSchema.getImgUrl());
			course.setPrice(courseSchema.getPrice());
			course.setRealPrice(courseSchema.getRealPrice());

			boolean isUserExist = false;
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			for (User user : userList) {
				if (user.getToken().equals(courseSchema.getToken()) && courseSchema.getToken().length() > 10) {
					course.setUserid(user.getId());
					isUserExist = true;
					break;
				}
			}

			if (isUserExist)
				courseRepo.insert(course);
			else
				throw new Exception("Нэвтрэх шаардлагатай");

			return ResponseSchema.getInstance(true);
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}

	@GetMapping("/lesson")
	public String getLesson() {
		try {
			return String.format("Successfull get lesson");
		} catch (Exception e) {
			return String.format("Aldaa garlaa: " + e.getMessage());
		}
	}

	/**
	 * Add a new lesson
	 * 
	 * @param lessonSchema lesson schema
	 * @course is the result of find by use has course id
	 * @return ResponseSchema.getInstance(true) when successful else
	 *         ResponseSchema.getInstance(false)
	 */
	@PostMapping("/lesson")
	public ResponseSchema addLesson(@RequestBody LessonSchema lessonSchema) {
		try {
			Lesson lesson = new Lesson();

			boolean isUserExist = false;
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			for (User user : userList) {
				if (user.getToken().equals(lessonSchema.getToken()) && lessonSchema.getToken().length() > 10) {
					Course course = courseRepo.findById(lessonSchema.getCourseid()).get();
					if (course.getUserid().equals(user.getId())) {
						isUserExist = true;
						break;
					}
				}
			}

			if (!isUserExist)
				throw new Exception("Нэвтрэх шаардлагатай");
				

			lesson.setIsFree(lessonSchema.getIsFree());
			lesson.setName(lessonSchema.getName());
			lesson.setTime(lessonSchema.getTime());
			lesson.setVideoUrl(lessonSchema.getVideoUrl());
			lesson.setCourseid(lessonSchema.getCourseid());

			lessonRepo.insert(lesson);
			return ResponseSchema.getInstance(true);
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
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
			String salt = PasswordUtils.getSalt(10);
			user.setSalt(salt);
			user.setPass(PasswordUtils.generateSecurePassword(userSchema.getPass(), salt));

			userRepo.insert(user);
			return ResponseSchema.getInstance(true);
		} catch (Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}

	@PostMapping("/userlogin")
	public LoginResponse loginUser(@RequestBody LoginSchema loginSchema) {
		try {
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();

			for (User user : userList) {
				if (loginSchema.getEmail().equals(user.getEmail())) {
					if (PasswordUtils.verifyUserPassword(loginSchema.getPass(), user.getPass(), user.getSalt())) {
						String saltString = PasswordUtils.getSalt(10);
						String tokenString = PasswordUtils.generateSecurePassword(user.getEmail() + user.getPass(),
								saltString);
						user.setToken(tokenString);
						userRepo.save(user);
						return new LoginResponse(tokenString);
					}
				}
			}
			throw new Exception("Цахим шуудан эсвэл нууц үг таарахгүй байна");
		} catch (Exception e) {
			return new LoginResponse(false, e.getMessage());
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
