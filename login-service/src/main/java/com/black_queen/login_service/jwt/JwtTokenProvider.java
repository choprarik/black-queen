/**
 * 
 */
package com.black_queen.login_service.jwt;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author rakshit.chopra
 *
 */
@Component
public class JwtTokenProvider {
	
	private String secretKey;
	
	@Autowired
	JwtProperties jwtProperties;
	
	@PostConstruct()
	public void init() {
		this.setSecretKey(Base64.encode(jwtProperties.getSecretKey().getBytes()));
	}
	
	public String createToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuer("")
				.setExpiration(this.calculateExpirationDat())
				.signWith(SignatureAlgorithm.HS256, this.getSecretKey())
				.compact();
	}
	
	private Date calculateExpirationDat() {
		Date now = new Date();
		return new Date(now.getTime() + Integer.parseInt(jwtProperties.getTokenValidityMilliSeconds()));
	}
	
	/**
	 * Gets secret key
	 * @return
	 */
	public String getSecretKey() {
		return this.secretKey;
	}
	/**
	 * Sets secret key
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
