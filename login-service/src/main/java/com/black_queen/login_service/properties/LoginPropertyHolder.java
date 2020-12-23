/**
 * 
 */
package com.black_queen.login_service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author rakshit.chopra
 *
 */
@Configuration
public class LoginPropertyHolder {
	
	@Value("${invalid.user.key}")
	private String invalidUserKey;
	
	@Value("${invalid.user.message}")
	private String invalidUserMessage;

	/**
	 * @return the invalidUserKey
	 */
	public String getInvalidUserKey() {
		return invalidUserKey;
	}

	/**
	 * @return the invalidUserMessage
	 */
	public String getInvalidUserMessage() {
		return invalidUserMessage;
	}
}
