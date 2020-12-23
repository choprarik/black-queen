package com.black_queen.user_management_service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.User;
import com.black_queen.user_management_service.repository.UserRepository;

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
			if (users == null) {
				throw new UserNotFoundException();
			}
		} catch(Exception e) {
			throw e;
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
			if (user == null) {
				throw new UserNotFoundException();
			}
		} catch(Exception e) {
			throw e;
		}
		return user;
	}

	/**
	 * Add a user to DB.
	 * @param user
	 * @return id of added user.
	 * @throws InternalServerException
	 */
	public String addUser(User user) throws Exception {
		String id = null;
		try {
			User newUser = userRepo.save(user);
			if (newUser != null) {
				id = newUser.getId();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		}
		return id;
	}

	/**
	 * Update an existing user in DB.
	 * @param user
	 * @return updated user
	 * @throws InternalServerException
	 */
	public User updateUser(User user) {
		User updatedUser = null;
		try {
			updatedUser = userRepo.save(user);
		} catch (Exception e) {
			throw e;
		}
		return updatedUser;
	}

	/**
	 * Delete user from DB.
	 * @param id
	 * @throws InternalServerException
	 */
	public void deleteUser(String id) {
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			throw e;
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
