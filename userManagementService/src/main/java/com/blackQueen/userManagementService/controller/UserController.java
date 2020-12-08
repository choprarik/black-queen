package com.blackQueen.userManagementService.controller;

import java.util.UUID;

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
	
	/**
	 * GET API for lists all the users registered in the system.
	 * @return List of users
	 * @throws UserNotFoundException
	 */
	@GetMapping("/users")
	public UserResponseVO getUsers() throws UserNotFoundException {
		UserResponseVO responseVO = new UserResponseVO();
		responseVO.setUsers(userService.getUsers());
		return responseVO;
	}
	
	/**
	 * GET API returns a user for the provided id.
	 * @param id - Users id
	 * @return - User object
	 * @throws UserNotFoundException
	 * @throws InvalidRequestException
	 */
	@GetMapping("/user")
	public User getUser(@RequestParam("id") String id) throws UserNotFoundException, InvalidRequestException {
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		return userService.getUser(id);
	}
	
	/**
	 * POST API for adding a new user.
	 * @param user - User object
	 * @return ID of added user
	 * @throws InvalidRequestException
	 * @throws InternalServerException
	 */
	@PostMapping("/user")
	public ResponseEntity<ErrorResponseVO> addUser(@RequestBody User user) throws InvalidRequestException, InternalServerException {
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
			throw new InvalidRequestException();
		}
		// set UUID for every new user
		user.setId(UUID.randomUUID().toString());
		String id = userService.addUser(user);
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.CREATED, propertiesComponent.getUserCreatedKey(), propertiesComponent.getMessage(propertiesComponent.getUserCreatedKey(), id)), HttpStatus.CREATED);
	}
	
	/**
	 * PUT API for updating a user data.
	 * @param user - changed User object.
	 * @return updated User object.
	 * @throws InvalidRequestException
	 * @throws InternalServerException
	 * @throws UserNotFoundException 
	 */
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) throws InvalidRequestException, InternalServerException, UserNotFoundException {
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
			throw new InvalidRequestException();
		}
		return userService.updateUser(user);
	}
	
	/**
	 * DELETE API for deleting a user.
	 * @param id - Id of user to be deleted.
	 * @throws InvalidRequestException
	 * @throws InternalServerException
	 * @throws UserNotFoundException 
	 */
	@DeleteMapping("/user")
	public void deleteUser(@RequestParam("id") String id) throws InvalidRequestException, InternalServerException, UserNotFoundException {
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		userService.deleteUser(id);
	}
}
