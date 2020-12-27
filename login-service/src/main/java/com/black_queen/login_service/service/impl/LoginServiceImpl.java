/**
 * 
 */
package com.black_queen.login_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.models.LoginData;
import com.black_queen.commons.models.SignUp;
import com.black_queen.login_service.clients.UserClient;
import com.black_queen.login_service.dao.LoginDataDao;
import com.black_queen.login_service.exceptions.InvalidUsernameOrPassword;
import com.black_queen.login_service.jwt.JwtTokenProvider;
import com.black_queen.login_service.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rakshit.chopra
 *
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDataDao loginDao;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserClient userClient;

	@Override
	public String login(LoginData loginInfo) throws InvalidUsernameOrPassword {
		LoginData fetchedData = null;
		String userId = "";
		fetchedData = loginDao.getLoginData(loginInfo.getUsername());
		if (loginInfo.getPasscode().equals(fetchedData.getPasscode())) {
			userId = fetchedData.getUserId();
		} else {
			throw new InvalidUsernameOrPassword();
		}
		return userId;
		
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String signUp(SignUp signUpInfo) throws Exception {
		String userId = userClient.getNewUser(signUpInfo.getUserInfo());
		signUpInfo.getLoginInfo().setUserId(userId);
		LoginData loginInfo = this.setLoginInfoOnSignUp(signUpInfo.getLoginInfo());
		return this.login(loginInfo);
	}
	
	private LoginData setLoginInfoOnSignUp(LoginData loginData) throws Exception {
		return loginDao.setLoginData(loginData);
	}

}
