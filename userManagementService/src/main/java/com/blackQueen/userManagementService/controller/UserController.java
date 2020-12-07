package com.blackQueen.userManagementService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackQueen.userManagementService.models.User;
import com.blackQueen.userManagementService.response.UserResponseVO;
import com.blackQueen.userManagementService.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public UserResponseVO getUsers() {
		UserResponseVO responseVO = new UserResponseVO();
		responseVO.setUsers(userService.getUsers());
		return responseVO;
	}
	
	@GetMapping("/user")
	public User getUser(@RequestParam("id") String id) {
		return userService.getUser(id);
	}
	
	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestParam("id") String id) {
		userService.deleteUser(id);
	}
}
