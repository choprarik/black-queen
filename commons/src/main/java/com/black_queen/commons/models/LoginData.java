/**
 * 
 */
package com.black_queen.commons.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

/**
 * @author rakshit.chopra
 *
 */
@Document(collection = "creds")
public class LoginData {
	
	@Id
	private String username;
	@NonNull
	private String passcode;
	private String userId;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the passcode
	 */
	public String getPasscode() {
		return passcode;
	}
	/**
	 * @param passcode the passcode to set
	 */
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
