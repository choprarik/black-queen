package com.blackQueen.userManagementService.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blackQueen.userManagementService.exceptions.InternalServerException;
import com.blackQueen.userManagementService.exceptions.InvalidRequestException;
import com.blackQueen.userManagementService.exceptions.UserNotFoundException;
import com.blackQueen.userManagementService.properties.PropertiesHolder;
import com.blackQueen.userManagementService.response.ErrorResponseVO;

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
	
	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseVO> handleInternalServerError(InternalServerException ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, propertiesComponent.getInternalServerKey(), propertiesComponent.getMessage(propertiesComponent.getInternalServerKey())), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseVO> handleUnknownExceptions(Exception ex) {
		return new ResponseEntity<ErrorResponseVO>(new ErrorResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, propertiesComponent.getUnkownExceptionKey(), propertiesComponent.getMessage(propertiesComponent.getUnkownExceptionKey())), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
