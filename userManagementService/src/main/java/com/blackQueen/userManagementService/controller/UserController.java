package com.blackQueen.userManagementService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.InvalidRequestException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;
import com.blackQueen.userManagementService.models.User;
import com.blackQueen.userManagementService.properties.PropertiesHolder;
import com.blackQueen.userManagementService.response.ErrorResponseVO;
import com.blackQueen.userManagementService.response.UserResponseVO;
import com.blackQueen.userManagementService.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PropertiesHolder propertiesComponent;
	
	@GetMapping("/users")
	public UserResponseVO getUsers() throws UserNotFoundException {
		UserResponseVO responseVO = new UserResponseVO();
		responseVO.setUsers(userService.getUsers());
		return responseVO;
	}
	
	@GetMapping("/user")
	public User getUser(@RequestParam("id") String id) throws UserNotFoundException, InvalidRequestException {
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		return userService.getUser(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<ErrorResponseVO> addUser(@RequestBody User user) throws InvalidRequestException, InternalServerException {
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
			throw new InvalidRequestException();
		}
		String id = userService.addUser(user);
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.CREATED, propertiesComponent.getUserCreatedKey(), propertiesComponent.getMessage(propertiesComponent.getUserCreatedKey(), id)), HttpStatus.CREATED);
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) throws InvalidRequestException, InternalServerException {
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
			throw new InvalidRequestException();
		}
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestParam("id") String id) throws InvalidRequestException, InternalServerException {
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		userService.deleteUser(id);
	}
}
