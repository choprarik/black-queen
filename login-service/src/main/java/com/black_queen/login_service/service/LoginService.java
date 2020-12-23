/**
 * 
 */
package com.black_queen.login_service.service;

import com.black_queen.commons.models.LoginData;
import com.black_queen.commons.models.SignUp;
import com.black_queen.login_service.exceptions.InvalidUsernameOrPassword;

/**
 * @author rakshit.chopra
 *
 */
public interface LoginService {
	
	public String login(LoginData loginInfo) throws InvalidUsernameOrPassword;
	
	public boolean logout();

	public String signUp(SignUp signUpInfo) throws InvalidUsernameOrPassword, Exception;
}
