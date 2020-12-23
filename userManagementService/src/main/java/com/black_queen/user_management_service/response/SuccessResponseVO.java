/**
 * 
 */
package com.black_queen.user_management_service.response;

import org.springframework.http.HttpStatus;

/**
 * @author rakshit.chopra
 *
 */
public class SuccessResponseVO {
	
	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public SuccessResponseVO(HttpStatus status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}
	private HttpStatus status;
	private String code;
	private String message;
	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
