package com.mongol.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongol.model.Purchase;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, Long> {
    
}
