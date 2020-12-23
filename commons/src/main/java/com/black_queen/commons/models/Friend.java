/**
 * 
 */
package com.black_queen.commons.models;

import org.springframework.lang.NonNull;

/**
 * @author rakshit.chopra
 *
 */
public class Friend {
	
	@NonNull
	private String name;
	@NonNull
	private String userId;
	@NonNull
	private String email;
	
	public Friend(String name, String userId, String email) {
		super();
		this.name = name;
		this.userId = userId;
		this.email = email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
