/**
 * 
 */
package com.black_queen.room_management_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.black_queen.commons.models.User;

/**
 * @author rakshit.chopra
 *
 */
@FeignClient("userManagement")
public interface UserClient {
	
	@GetMapping("/user?id={id}")
    public User getUser(@PathVariable String id);
}
