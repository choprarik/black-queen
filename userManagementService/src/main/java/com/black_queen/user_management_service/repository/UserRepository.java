package com.black_queen.user_management_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.black_queen.commons.models.User;

public interface UserRepository extends MongoRepository<User, String>{	
}
