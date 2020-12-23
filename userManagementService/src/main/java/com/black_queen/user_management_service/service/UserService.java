package com.black_queen.user_management_service.service;


import java.util.List;

import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.User;

public interface UserService {
	
	/**
	 * Returns a list of users.
	 * @return List of users.
	 * @throws UserNotFoundException
	 */
	public List<User> getUsers() throws UserNotFoundException;
	
	/**
	 * Gets a user for provided ID.
	 * @param id
	 * @return concerned user
	 * @throws UserNotFoundException
	 */
	public User getUser(String id) throws UserNotFoundException;
	
	/**
	 * Add a new user
	 * @param user
	 * @return ID of new user
	 * @throws InternalServerException
	 */
	public String addUser(User user) throws Exception;
	
	/**
	 * Update an existing user
	 * @param user
	 * @return updated user object
	 * @throws InternalServerException
	 * @throws UserNotFoundException 
	 */
	public User updateUser(User user) throws UserNotFoundException;
	
	/**
	 * Delete an existing user
	 * @param id
	 * @throws InternalServerException
	 * @throws UserNotFoundException 
	 * @throws com.black_queen.commons.exceptions.UserNotFoundException 
	 */
	public void deleteUser(String id) throws UserNotFoundException;
}
