package com.blackQueen.userManagementService.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackQueen.userManagementService.dbRepo.UserRepository;
import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;
import com.blackQueen.userManagementService.models.User;

@Service
public class UserManagementDao {

	@Autowired
	UserRepository userRepo;

	public List<User> getUsers() throws UserNotFoundException {
		List<User> users = null;
		try {
			users = userRepo.findAll();
		} catch(Exception e) {
			throw new UserNotFoundException();
		}
		return users;
	}

	public User getUser(String id) throws UserNotFoundException {
		User user = null;
		try {
			user = userRepo.findById(id).get();
		} catch(Exception e) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public String addUser(User user) throws InternalServerException {
		String id = null;
		try {
			User newUser = userRepo.save(user);
			id = newUser.getId();
		} catch (Exception e) {
			throw new InternalServerException();
		}
		return id;
	}

	public User updateUser(User user) throws InternalServerException {
		User updatedUser = null;
		try {
			updatedUser = userRepo.save(user);
		} catch (Exception e) {
			throw new InternalServerException();
		}
		return updatedUser;
	}

	public void deleteUser(String id) throws InternalServerException {
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerException();
		}
	}
}
