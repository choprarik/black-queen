package com.black_queen.commons.models;

/**
 * @author rakshit.chopra
 *
 */
public class SignUp {
	private LoginData loginInfo;
	private User userInfo;
	/**
	 * @return the loginInfo
	 */
	public LoginData getLoginInfo() {
		return loginInfo;
	}
	/**
	 * @param loginInfo the loginInfo to set
	 */
	public void setLoginInfo(LoginData loginInfo) {
		this.loginInfo = loginInfo;
	}
	/**
	 * @return the userInfo
	 */
	public User getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
}
