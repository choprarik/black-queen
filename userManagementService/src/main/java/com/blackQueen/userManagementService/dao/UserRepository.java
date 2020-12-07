package com.blackQueen.userManagementService.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blackQueen.userManagementService.models.User;

public interface UserRepository extends MongoRepository<User, String>{	
}
