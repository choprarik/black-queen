package com.black_queen.user_management_service.response;
import java.util.List;

import com.black_queen.commons.models.User;

public class UserResponseVO {
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
