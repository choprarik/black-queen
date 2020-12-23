/**
 * 
 */
package com.black_queen.user_management_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.exceptions.FriendAlreadyExistsException;
import com.black_queen.commons.exceptions.FriendNotExistsException;
import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.Friend;
import com.black_queen.commons.models.User;
import com.black_queen.user_management_service.service.FriendService;
import com.black_queen.user_management_service.service.UserService;

/**
 * @author rakshit.chopra
 *
 */
@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	UserService userService;
	
	@Override
	public List<Friend> getFriendsForUser(String userId) throws UserNotFoundException {
		User user = userService.getUser(userId);
		return user.getFriends();
	}

	@Override
	public void addFriendForUser(String userId, Friend friend) throws UserNotFoundException, FriendAlreadyExistsException {
		User user = userService.getUser(userId);
		List<Friend> friends = user.getFriends();
		Friend f = this.getAFriend(friends, friend.getUserId());
		if (f == null) {
			friends.add(friend);
			user.setFriends(friends);
			userService.updateUser(user);
		} else {
			throw new FriendAlreadyExistsException();
		}
	}

	@Override
	public void deleteFriendForUser(String userId, Friend friend) throws UserNotFoundException, FriendNotExistsException {
		User user = userService.getUser(userId);
		List<Friend> friends = user.getFriends();
		Friend f = this.getAFriend(friends, friend.getUserId());
		if (f != null) {
			friends.remove(f);
			user.setFriends(friends);
			userService.updateUser(user);
		} else {
			throw new FriendNotExistsException();
		}
	}
	
	@Override
	public List<Friend> getPotentialFriend(String userId) throws UserNotFoundException {
		List<Friend> friends = new ArrayList<Friend>();
		User user = userService.getUser(userId);
		List<Friend> userFriends = user.getFriends();
		List<User> users = userService.getUsers();
		if (users.size() > 0) {
			for (User u: users) {
				Friend f = new Friend(u.getName(), u.getId(), u.getEmail());
				if (u.getId() != user.getId() && !userFriends.contains(f) && !friends.contains(f)) {
					friends.add(f);
				}
			}
		}
		return friends;
	}
	
	private Friend getAFriend(List<Friend> friends, String friendId) {
		Friend f = null;
		if (friends.size() > 0 && !friendId.isEmpty()) {
			for (Friend friend : friends) {
				if (friendId.trim().equalsIgnoreCase(friend.getUserId().trim())) {
					f = friend;
				}
			}
		}
		return f;
	}

}
