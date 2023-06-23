package com.mongol.udemy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mongol.model.*;
import com.mongol.repo.*;

import schema.*;
import util.PasswordUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MainController {
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/purchase")
	public ResponseSchema addPurchase(@RequestHeader("Authorization") String token, @RequestBody Purchase body) {
		try {
			boolean isValid = false;
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			
			for(User user : userList) {
				if(user.getId().equals(body.getUserId())) {
					if(body.getCourseId() >= 0 && body.getCourseId() < user.getCreatedCourseList().size())
						isValid = true;
					break;
				}
			}
			
			if(isValid) {
				for(User user : userList) {
					if(user.getToken().equals(token) && token.length() > 10) {
						user.getPurchasesList().add(body);
						userRepo.save(user);
						return ResponseSchema.getInstance(true);
					}
				}
			}
			throw new Exception("Course id эсвэл Teacher id таарахгүй байна");
			
		} catch(Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@GetMapping("/cart")
	public List<AllCourseSchema> getCart(@RequestHeader("Authorization") String token) {
		try {
			List<AllCourseSchema> courseResList = new ArrayList<>();
			
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			for(User user : userList) {
				if(user.getToken().equals(token) && token.length() > 10) {
					for(Purchase purchase : user.getPurchasesList()) {
						AllCourseSchema data = new AllCourseSchema();
						data.setCourseId(purchase.getCourseId());
						data.setUserId(purchase.getUserId());
						data.setCourse(userRepo.findById(purchase.getUserId()).get().getCreatedCourseList().get(purchase.getCourseId()));
						courseResList.add(data);
					}
				}
			}
			return courseResList;
		} catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("/getCourseById")
	public Course getCourseById(@RequestParam Integer courseid, @RequestParam Long teacherid, @RequestHeader("Authorization") String token) {
		try {
			boolean isPurchased = false;
			List<Purchase> purchaseList = new ArrayList<>();
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			for(User user : userList) {
				if(user.getToken().equals(token) && token.length() > 10) {
					purchaseList = user.getPurchasesList();
					break;
				}
			}
			
			User teacher = userRepo.findById(teacherid).get();
			Course course = teacher.getCreatedCourseList().get(courseid);
			
			for(Purchase purchase : purchaseList) {
				if(purchase.getCourseId().equals(courseid) && purchase.getUserId().equals(teacherid)) {
					isPurchased = true;
					break;
				}
			}
			
			if(!isPurchased) {
				for(int i = 0; i < course.getLessonList().size(); i++) {
					if(!course.getLessonList().get(i).getIsFree()) {
						course.getLessonList().get(i).setVideoUrl(null);
					}
				}
			}
			
			return course;
		} catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("/course")
	public List<AllCourseSchema> getCourse() {
		try {
			List<AllCourseSchema> courseResList = new ArrayList<>();
			
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			
			for(User user : userList) {
				for(int i=0; i < user.getCreatedCourseList().size(); i++) {
					AllCourseSchema data = new AllCourseSchema();
					data.setCourse(user.getCreatedCourseList().get(i));
					data.setCourseId(i);
					data.setUserId(user.getId());
					data.setTeacherName(user.getName());
					courseResList.add(data);
				}
 			}
			
			return courseResList;
		} catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("/course")
	public ResponseSchema addCourse(@RequestBody CourseSchema scheme, @RequestHeader("Authorization") String token) {
		try {
			if(scheme.getName() == null) 
				throw new Exception("Заавал сургалтын нэр оруулах ёстой");
			
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			
			for(User user : userList) {
				if(user.getToken().equals(token) && token.length() > 10) {
					Course course = new Course();
					course.setName(scheme.getName());
					course.setImgUrl(scheme.getImgUrl());
					course.setPrice(scheme.getPrice());
					course.setRealPrice(scheme.getRealPrice());
					
					user.getCreatedCourseList().add(course);
					userRepo.save(user);
					return ResponseSchema.getInstance(true);
				}
			}
			
			throw new Exception("Нэвтрэх шаардлагатай");
		} catch(Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@PostMapping("/lesson")
	public ResponseSchema addLesson(@RequestBody LessonSchema scheme, @RequestHeader("Authorization") String token) {
		try {
			List<User> userList = new ArrayList<>();
			userList = userRepo.findAll();
			for(User user : userList) {
				if(user.getToken().equals(token) && token.length() > 10) {
					if(scheme.getCourseid() < 0 || scheme.getCourseid() >= user.getCreatedCourseList().size())
						throw new Exception("Course Id буруу байна.");
					Lesson lesson = new Lesson();
					lesson.setIsFree(scheme.getIsFree());
					lesson.setName(scheme.getName());
					lesson.setTime(scheme.getTime());
					lesson.setVideoUrl(scheme.getVideoUrl());
					Course course = user.getCreatedCourseList().get(scheme.getCourseid());
					course.getLessonList().add(lesson);
					userRepo.save(user);
					return ResponseSchema.getInstance(true);
				}
			}
			
			throw new Exception("Нэвтрэх шаардлагатай");
		} catch(Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@PostMapping("/user")
	public ResponseSchema addUser(@RequestBody UserSchema scheme) {
		try {
			User user = new User();
			user.setName(scheme.getName());
			user.setEmail(scheme.getEmail());
			
			String salt = PasswordUtils.getSalt(10);
			user.setSalt(salt);
			user.setPass(PasswordUtils.generateSecurePassword(scheme.getPass(), salt));
			
			userRepo.insert(user);
			return ResponseSchema.getInstance(true);
		} catch(Exception e) {
			return ResponseSchema.getInstance(false, e.getMessage());
		}
	}
	
	@PostMapping("/userLogin")
	public LoginResponse loginUser(@RequestBody LoginSchema scheme) {
		try {
			List<User> userList = new ArrayList<>();
			
			userList = userRepo.findAll();
			for(User user : userList) {
				if(scheme.getEmail().equals(user.getEmail())) {
					if(PasswordUtils.verifyUserPassword(scheme.getPass(), user.getPass(), user.getSalt())) {
						String salt = PasswordUtils.getSalt(10);
						String token = PasswordUtils.generateSecurePassword(user.getEmail() + user.getPass(), salt);
						user.setToken(token);
						userRepo.save(user);
						return new LoginResponse(token);
					}
				}
			}
			throw new Exception("Цахим шуудан эсвэл нууц үг таарахгүй байна.");
		} catch(Exception e) {
			return new LoginResponse(false, e.getMessage());
		}
	}
	
}
