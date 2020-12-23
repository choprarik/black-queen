package com.black_queen.user_management_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.User;
import com.black_queen.user_management_service.dao.UserManagementDao;
import com.black_queen.user_management_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserManagementDao userDao;

	public List<User> getUsers() throws UserNotFoundException {
		return userDao.getUsers();
	}

	public User getUser(String id) throws UserNotFoundException {
		return userDao.getUser(id);
	}

	public String addUser(User user) throws Exception {
		return userDao.addUser(user);
	}

	public User updateUser(User user) throws UserNotFoundException {
		User updatedUser = null;
		if (userDao.isUserExists(user.getId())) {
			updatedUser = userDao.updateUser(user);	
		} else {
			throw new UserNotFoundException();
		}
		return updatedUser;
	}

	public void deleteUser(String id) throws UserNotFoundException {
		if (userDao.isUserExists(id)) {
			userDao.deleteUser(id);
		} else {
			throw new UserNotFoundException();
		}
	}

}
