package com.blackQueen.userManagementService.dbRepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blackQueen.commons.models.User;

public interface UserRepository extends MongoRepository<User, String>{	
}
