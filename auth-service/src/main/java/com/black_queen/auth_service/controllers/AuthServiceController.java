/**
 * 
 */
package com.black_queen.auth_service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.black_queen.commons.models.LoginData;

/**
 * @author rakshit.chopra
 *
 */
@RestController
public class AuthServiceController {
	
	@PostMapping("/api/login")
	public String login(@RequestBody LoginData login) {
		return null;
	}
}
