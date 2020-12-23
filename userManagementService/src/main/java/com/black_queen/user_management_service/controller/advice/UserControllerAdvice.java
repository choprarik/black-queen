package com.black_queen.user_management_service.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.black_queen.commons.exceptions.FriendAlreadyExistsException;
import com.black_queen.commons.exceptions.FriendNotExistsException;
import com.black_queen.commons.exceptions.InvalidRequestException;
import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.user_management_service.properties.PropertiesHolder;
import com.black_queen.user_management_service.response.ErrorResponseVO;

@ControllerAdvice
public class UserControllerAdvice {
	
	@Autowired
	PropertiesHolder propertiesComponent;
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseVO> handleBadRequest(InvalidRequestException ex) {
		return ResponseEntity.badRequest().body(new ErrorResponseVO(HttpStatus.BAD_REQUEST, propertiesComponent.getInvalidRequestKey(), propertiesComponent.getMessage(propertiesComponent.getInvalidRequestKey())));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponseVO> handleNotFoundResponse(UserNotFoundException ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.NOT_FOUND, propertiesComponent.getUserNotFoundKey(), propertiesComponent.getMessage(propertiesComponent.getUserNotFoundKey())), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FriendNotExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponseVO> handleFriendNotFoundResponse(FriendNotExistsException ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.NOT_FOUND, propertiesComponent.getFriendNotFoundKey(), propertiesComponent.getMessage(propertiesComponent.getFriendNotFoundKey())), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FriendAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<ErrorResponseVO> handleFriendAlreadyExistResponse(FriendAlreadyExistsException ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.NOT_ACCEPTABLE, propertiesComponent.getFriendAlreadyExistsKey(), propertiesComponent.getMessage(propertiesComponent.getFriendAlreadyExistsKey())), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseVO> handleInternalServerError(Exception ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, propertiesComponent.getInternalServerKey(), propertiesComponent.getMessage(propertiesComponent.getInternalServerKey())), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
