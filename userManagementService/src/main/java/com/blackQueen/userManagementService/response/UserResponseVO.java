package com.blackQueen.userManagementService.response;
import java.util.List;

import com.blackQueen.userManagementService.models.User;

public class UserResponseVO {
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
