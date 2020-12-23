/**
 * 
 */
package com.black_queen.auth_service.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.black_queen.auth_service.security.jwt.JwtTokenProvider;
import com.black_queen.auth_service.services.AuthenticationService;
import com.black_queen.commons.models.LoginData;

/**
 * @author rakshit.chopra
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private Set<String> sessionManager = new HashSet<String>();
	
	@Autowired
	JwtTokenProvider jwtProvider;

	@Override
	public String loginAuth(LoginData login) {
		RestTemplate rest = new RestTemplate();
		String resp = rest.postForObject("http://localhost:8765/login", login, String.class);
		this.getSessionManager().add(resp);
		return resp;
	}

	/**
	 * @return the sessionManager
	 */
	public Set<String> getSessionManager() {
		return sessionManager;
	}

	/**
	 * @param sessionManager the sessionManager to set
	 */
	public void setSessionManager(Set<String> sessionManager) {
		this.sessionManager = sessionManager;
	}
	
	public boolean getAuthentication(String token) {
        String userId = jwtProvider.getUserId(token);
        return this.getSessionManager().contains(userId);
    }

}
