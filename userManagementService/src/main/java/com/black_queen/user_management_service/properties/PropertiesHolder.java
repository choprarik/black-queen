package com.black_queen.user_management_service.properties;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesHolder {
	
	@Value("#{${languageMap}}")
	private Map<String, String> languageMap;
	
	@Value("${error.user.not.found.key}")
	private String userNotFoundKey;
	
	@Value("${error.invalid.request.key}")
	private String invalidRequestKey;
	
	@Value("${error.unknown.exception.key}")
	private String unkownExceptionKey;
	
	@Value("${message.user.created.key}")
	private String userCreatedKey;
	
	@Value("${error.internal.server.key}")
	private String internalServerKey;
	
	@Value("error.friend.not.found.key")
	private String friendNotFoundKey;
	
	@Value("error.friend.exists.key")
	private String friendAlreadyExistsKey;
	
	@Value("message.friend.added.key")
	private String friendAddedKey;

	/**
	 * @return the userNotFoundKey
	 */
	public String getUserNotFoundKey() {
		return userNotFoundKey;
	}

	/**
	 * @return the invalidRequestKey
	 */
	public String getInvalidRequestKey() {
		return invalidRequestKey;
	}

	/**
	 * @return the unkownExceptionKey
	 */
	public String getUnkownExceptionKey() {
		return unkownExceptionKey;
	}

	/**
	 * @return the userCreatedKey
	 */
	public String getUserCreatedKey() {
		return userCreatedKey;
	}
	
	/**
	 * Returns message from the bundler
	 * @param key - error key
	 * @param args
	 * @return
	 */
	public String getMessage(String key, Object... args) {
		String str = languageMap.get(key);
		if (args.length > 0) {
			return String.format(str, args);
		}
		return str;
	}

	/**
	 * @return the internalServerKey
	 */
	public String getInternalServerKey() {
		return internalServerKey;
	}

	/**
	 * @return the friendNotFoundKey
	 */
	public String getFriendNotFoundKey() {
		return friendNotFoundKey;
	}

	/**
	 * @param friendNotFoundKey the friendNotFoundKey to set
	 */
	public void setFriendNotFoundKey(String friendNotFoundKey) {
		this.friendNotFoundKey = friendNotFoundKey;
	}

	/**
	 * @return the friendAlreadyExistsKey
	 */
	public String getFriendAlreadyExistsKey() {
		return friendAlreadyExistsKey;
	}

	/**
	 * @param friendAlreadyExistsKey the friendAlreadyExistsKey to set
	 */
	public void setFriendAlreadyExistsKey(String friendAlreadyExistsKey) {
		this.friendAlreadyExistsKey = friendAlreadyExistsKey;
	}

	/**
	 * @return the friendAddedKey
	 */
	public String getFriendAddedKey() {
		return friendAddedKey;
	}

	/**
	 * @param friendAddedKey the friendAddedKey to set
	 */
	public void setFriendAddedKey(String friendAddedKey) {
		this.friendAddedKey = friendAddedKey;
	}
	
}
