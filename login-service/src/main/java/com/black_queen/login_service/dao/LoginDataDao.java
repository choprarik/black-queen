/**
 * 
 */
package com.black_queen.login_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.models.LoginData;
import com.black_queen.login_service.exceptions.InvalidUsernameOrPassword;
import com.black_queen.login_service.repository.LoginDataRepository;

/**
 * @author rakshit.chopra
 *
 */
@Service
public class LoginDataDao {
	
	@Autowired
	LoginDataRepository loginRepository;
	
	/**
	 * Fetch login data from DB
	 * @param userName
	 * @return
	 * @throws InvalidUsernameOrPassword
	 */
	public LoginData getLoginData(String userName) throws InvalidUsernameOrPassword {
		LoginData loginInfo = null;
		try {
			loginInfo = loginRepository.findById(userName).get();
		} catch (Exception e) {
			throw new InvalidUsernameOrPassword();
		}
		if (loginInfo == null) {
			throw new InvalidUsernameOrPassword();
		}
		return loginInfo;
	}
	
	/**
	 * Set login info in DB
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	public LoginData setLoginData(LoginData loginInfo) throws Exception {
		LoginData updatedLoginInfo = null;
		try {
			updatedLoginInfo = loginRepository.save(loginInfo);
			if (updatedLoginInfo == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		}
		return updatedLoginInfo;
	}

}
