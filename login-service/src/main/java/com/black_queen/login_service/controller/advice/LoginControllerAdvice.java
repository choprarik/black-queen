/**
 * 
 */
package com.black_queen.login_service.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.black_queen.login_service.exceptions.InvalidUsernameOrPassword;
import com.black_queen.login_service.properties.LoginPropertyHolder;
import com.black_queen.login_service.response.ErrorLoginResponseVO;

/**
 * @author rakshit.chopra
 *
 */
@ControllerAdvice
public class LoginControllerAdvice {

	@Autowired
	LoginPropertyHolder loginProperties;
	
	@ExceptionHandler(InvalidUsernameOrPassword.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorLoginResponseVO> handleInvalidUserException(InvalidUsernameOrPassword ex) {
		ErrorLoginResponseVO err = new ErrorLoginResponseVO();
		err.setErrorCode(loginProperties.getInvalidUserKey());
		err.setMessage(loginProperties.getInvalidUserMessage());
		err.setException(ex.getMessage());
		return new ResponseEntity<ErrorLoginResponseVO>(err, HttpStatus.UNAUTHORIZED);
	}
}
