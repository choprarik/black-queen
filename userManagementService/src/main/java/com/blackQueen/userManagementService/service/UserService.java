package com.blackQueen.userManagementService.service;


import java.util.List;
import com.blackQueen.userManagementService.models.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User getUser(String id);
	
	public String addUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(String id);
}
