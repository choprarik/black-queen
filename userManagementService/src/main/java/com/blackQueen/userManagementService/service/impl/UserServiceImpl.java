package com.blackQueen.userManagementService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackQueen.userManagementService.dao.UserManagementDao;
import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;
import com.blackQueen.userManagementService.models.User;
import com.blackQueen.userManagementService.service.UserService;

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

	public String addUser(User user) throws InternalServerException {
		return userDao.addUser(user);
	}

	public User updateUser(User user) throws InternalServerException, UserNotFoundException {
		User updatedUser = null;
		if (userDao.isUserExists(user.getId())) {
			updatedUser = userDao.updateUser(user);	
		} else {
			throw new UserNotFoundException();
		}
		return updatedUser;
	}

	public void deleteUser(String id) throws InternalServerException, UserNotFoundException {
		if (userDao.isUserExists(id)) {
			userDao.deleteUser(id);
		} else {
			throw new UserNotFoundException();
		}
	}

}
