/**
 * UserAuthRepository.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.logger.Loggable;

@Repository
@RepositoryRestResource(collectionResourceRel = "userService.auth", path = "userService.auth")
@Loggable
public interface UserAuthRepository extends MongoRepository<User, String> {

    /**
     * Find by username.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findByUsername(String username);

}