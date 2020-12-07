package com.blackQueen.userManagementService.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackQueen.userManagementService.dao.UserRepository;
import com.blackQueen.userManagementService.models.User;
import com.blackQueen.userManagementService.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User getUser(String id) {
		return userRepo.findById(id).get();
	}

	public String addUser(User user) {
		User newUser = userRepo.save(user);
		return newUser.getId();
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUser(String id) {
		userRepo.deleteById(id);
	}

}
