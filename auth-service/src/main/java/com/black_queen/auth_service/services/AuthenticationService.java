package com.black_queen.auth_service.services;

import com.black_queen.commons.models.LoginData;

public interface AuthenticationService {
	
	public String loginAuth(LoginData login);

}
