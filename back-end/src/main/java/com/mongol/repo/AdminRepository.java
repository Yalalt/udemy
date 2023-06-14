package com.mongol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongol.model.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {
    
}
