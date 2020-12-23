/**
 * 
 */
package com.black_queen.login_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.black_queen.commons.models.User;

/**
 * @author rakshit.chopra
 *
 */
@FeignClient("userManagement")
public interface UserClient {

	@PostMapping(value = "/user", consumes = "application/json")
    public String getNewUser(User user);
}
