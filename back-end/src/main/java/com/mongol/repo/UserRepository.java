package com.mongol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongol.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    
}
