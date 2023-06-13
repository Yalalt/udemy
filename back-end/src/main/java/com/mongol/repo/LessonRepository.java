package com.mongol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongol.model.Lesson;

@Repository
public interface LessonRepository extends MongoRepository<Lesson, String>  {
    
}
