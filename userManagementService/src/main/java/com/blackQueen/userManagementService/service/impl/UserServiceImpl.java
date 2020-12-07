package com.blackQueen.userManagementService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackQueen.userManagementService.dao.UserManagementDao;
import com.blackQueen.userManagementService.models.User;
import com.blackQueen.userManagementService.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserManagementDao userDao;

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public User getUser(String id) {
		return userDao.getUser(id);
	}

	public String addUser(User user) {
		return userDao.addUser(user);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

}
