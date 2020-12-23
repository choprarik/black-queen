/**
 * 
 */
package com.black_queen.login_service.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author rakshit.chopra
 *
 */
@Configuration
public class JwtProperties {
	
	@Value("${secret.key}")
	private String secretKey;
	
	@Value("${token.validity.milliseconds}")
	private String tokenValidityMilliSeconds;

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the tokenValidityMilliSeconds
	 */
	public String getTokenValidityMilliSeconds() {
		return tokenValidityMilliSeconds;
	}

	/**
	 * @param tokenValidityMilliSeconds the tokenValidityMilliSeconds to set
	 */
	public void setTokenValidityMilliSeconds(String tokenValidityMilliSeconds) {
		this.tokenValidityMilliSeconds = tokenValidityMilliSeconds;
	}
}
