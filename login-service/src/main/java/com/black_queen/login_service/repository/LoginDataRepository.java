/**
 * 
 */
package com.black_queen.login_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.black_queen.commons.models.LoginData;

/**
 * @author rakshit.chopra
 *
 */
public interface LoginDataRepository extends MongoRepository<LoginData, String> {

}
