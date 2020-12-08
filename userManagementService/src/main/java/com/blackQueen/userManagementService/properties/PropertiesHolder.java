package com.blackQueen.userManagementService.properties;

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
	
	public String getMessage(String key, String... args) {
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
	
}
