/**
 * 
 */
package com.black_queen.user_management_service.service;

import java.util.List;

import com.black_queen.commons.exceptions.FriendAlreadyExistsException;
import com.black_queen.commons.exceptions.FriendNotExistsException;
import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.Friend;

/**
 * @author rakshit.chopra
 *
 */
public interface FriendService {
	
	/**
	 * Gets list of friends for a user
	 * @param userId
	 * @return
	 * @throws UserNotFoundException 
	 */
	public List<Friend> getFriendsForUser(String userId) throws UserNotFoundException;
	
	/**
	 * Adds a friend to user friend list
	 * @param userId
	 * @param friend
	 * @throws UserNotFoundException 
	 * @throws FriendAlreadyExistsException 
	 */
	public void addFriendForUser(String userId, Friend friend) throws UserNotFoundException, FriendAlreadyExistsException;
	
	/**
	 * Deletes a friend from user friend list
	 * @param userId
	 * @param friend
	 * @throws UserNotFoundException 
	 * @throws FriendNotExistsException 
	 */
	public void deleteFriendForUser(String userId, Friend friend) throws UserNotFoundException, FriendNotExistsException;

	/**
	 * Get list of potential friends
	 * @param userId
	 * @return
	 * @throws UserNotFoundException 
	 */
	public List<Friend> getPotentialFriend(String userId) throws UserNotFoundException;

}
