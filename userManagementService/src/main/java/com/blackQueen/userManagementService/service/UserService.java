package com.blackQueen.userManagementService.service;


import java.util.List;

import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;
import com.blackQueen.userManagementService.models.User;

public interface UserService {
	
	public List<User> getUsers() throws UserNotFoundException;
	
	public User getUser(String id) throws UserNotFoundException;
	
	public String addUser(User user) throws InternalServerException;
	
	public User updateUser(User user) throws InternalServerException;
	
	public void deleteUser(String id) throws InternalServerException;
}
