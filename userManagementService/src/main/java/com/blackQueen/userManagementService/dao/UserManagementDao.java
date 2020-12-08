package com.blackQueen.userManagementService.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackQueen.commons.models.User;
import com.blackQueen.userManagementService.dbRepo.UserRepository;
import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;

@Service
public class UserManagementDao {

	@Autowired
	UserRepository userRepo;

	/**
	 * Get list of users from DB.
	 * @return user list
	 * @throws UserNotFoundException
	 */
	public List<User> getUsers() throws UserNotFoundException {
		List<User> users = null;
		try {
			users = userRepo.findAll();
		} catch(Exception e) {
			throw new UserNotFoundException();
		}
		return users;
	}

	/**
	 * Get user for id from DB.
	 * @param id
	 * @return user item
	 * @throws UserNotFoundException
	 */
	public User getUser(String id) throws UserNotFoundException {
		User user = null;
		try {
			user = userRepo.findById(id).get();
		} catch(Exception e) {
			throw new UserNotFoundException();
		}
		return user;
	}

	/**
	 * Add a user to DB.
	 * @param user
	 * @return id of added user.
	 * @throws InternalServerException
	 */
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

	/**
	 * Update an existing user in DB.
	 * @param user
	 * @return updated user
	 * @throws InternalServerException
	 */
	public User updateUser(User user) throws InternalServerException {
		User updatedUser = null;
		try {
			updatedUser = userRepo.save(user);
		} catch (Exception e) {
			throw new InternalServerException();
		}
		return updatedUser;
	}

	/**
	 * Delete user from DB.
	 * @param id
	 * @throws InternalServerException
	 */
	public void deleteUser(String id) throws InternalServerException {
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerException();
		}
	}
	
	/**
	 * Returns true if user exists.
	 * @param id
	 * @return
	 */
	public boolean isUserExists(String id) {
		return userRepo.existsById(id);
	}
}
