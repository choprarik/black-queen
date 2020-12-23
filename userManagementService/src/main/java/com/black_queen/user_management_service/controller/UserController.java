package com.black_queen.user_management_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.black_queen.commons.exceptions.FriendAlreadyExistsException;
import com.black_queen.commons.exceptions.InvalidRequestException;
import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.Friend;
import com.black_queen.commons.models.User;
import com.black_queen.user_management_service.properties.PropertiesHolder;
import com.black_queen.user_management_service.response.SuccessResponseVO;
import com.black_queen.user_management_service.response.UserResponseVO;
import com.black_queen.user_management_service.service.FriendService;
import com.black_queen.user_management_service.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FriendService friendService;
	
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
	 * @throws Exception 
	 * @throws InternalServerException
	 */
	@PostMapping("/user")
	public ResponseEntity<String> addUser(@RequestBody User user) throws Exception {
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
			throw new InvalidRequestException();
		}
		// set UUID for every new user
		user.setId(UUID.randomUUID().toString());
		String id = userService.addUser(user);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
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
	public User updateUser(@RequestBody User user) throws InvalidRequestException, UserNotFoundException {
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
	public void deleteUser(@RequestParam("id") String id) throws InvalidRequestException, UserNotFoundException {
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		userService.deleteUser(id);
	}
	
	@GetMapping("/user/{userId}/friends")
	public List<Friend> getFriends(@PathVariable String userId) throws UserNotFoundException {
		return friendService.getFriendsForUser(userId);
	}
	
	@PostMapping("/user/{userId}/friend")
	public ResponseEntity<SuccessResponseVO> addFriends(@RequestBody Friend friend, @PathVariable String userId) throws UserNotFoundException, FriendAlreadyExistsException {
		friendService.addFriendForUser(userId, friend);
		return new ResponseEntity<SuccessResponseVO>(new SuccessResponseVO(HttpStatus.CREATED, propertiesComponent.getFriendAddedKey(), propertiesComponent.getMessage(propertiesComponent.getFriendAddedKey())), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/potentials")
	public List<Friend> getPotentialFriends(@PathVariable String userId) throws UserNotFoundException {
		return friendService.getPotentialFriend(userId);
	}
}
