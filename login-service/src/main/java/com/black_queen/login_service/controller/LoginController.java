/**
 * 
 */
package com.black_queen.login_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.black_queen.commons.models.IdDTO;
import com.black_queen.commons.models.LoginData;
import com.black_queen.commons.models.SignUp;
import com.black_queen.login_service.exceptions.InvalidUsernameOrPassword;
import com.black_queen.login_service.service.LoginService;

/**
 * @author rakshit.chopra
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<IdDTO> login(@RequestBody LoginData loginInfo) throws InvalidUsernameOrPassword {
		if (loginInfo.getUsername().isEmpty() || loginInfo.getPasscode().isEmpty()) {
			throw new InvalidUsernameOrPassword();
		}
		String resp = loginService.login(loginInfo);
		IdDTO result = new IdDTO();
		result.setId(resp);
		return new ResponseEntity<IdDTO>(result, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<IdDTO> signUp(@RequestBody SignUp signUpInfo) throws Exception {
		if (signUpInfo.getLoginInfo().getUsername().isEmpty() || signUpInfo.getLoginInfo().getPasscode().isEmpty()) {
			throw new InvalidUsernameOrPassword();
		}
		String resp = null;
		try {
			resp = loginService.signUp(signUpInfo);
		} catch (Exception e) {
			throw e;
		}
		IdDTO result = new IdDTO();
		result.setId(resp);
		return new ResponseEntity<IdDTO>(result, HttpStatus.OK);
	}
	
}
