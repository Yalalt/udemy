package com.mongol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongol.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    
}
