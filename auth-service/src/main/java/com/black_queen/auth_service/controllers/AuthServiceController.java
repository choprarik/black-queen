/**
 * 
 */
package com.black_queen.auth_service.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.black_queen.auth_service.security.jwt.JwtTokenProvider;
import com.black_queen.auth_service.services.AuthenticationService;
import com.black_queen.commons.models.LoginData;

/**
 * @author rakshit.chopra
 *
 */
@RestController
public class AuthServiceController {
	
	@Autowired
	AuthenticationService authService;
	
	@Autowired
	JwtTokenProvider jwtProvider;
	
	@PostMapping("/api/login")
	public String login(@RequestBody LoginData login, HttpServletResponse response) {
		String userId = authService.loginAuth(login);
		String token = jwtProvider.getJwt(userId);
		Cookie tokenCookie = new Cookie("JWT", token);
		response.addCookie(tokenCookie);
		return userId;
	}
}
