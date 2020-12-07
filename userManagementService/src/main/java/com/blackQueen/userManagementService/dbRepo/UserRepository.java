package com.blackQueen.userManagementService.dbRepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blackQueen.userManagementService.models.User;

public interface UserRepository extends MongoRepository<User, String>{	
}
