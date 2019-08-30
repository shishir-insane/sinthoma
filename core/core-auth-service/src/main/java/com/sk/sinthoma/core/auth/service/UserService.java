/**
 * UserService.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.service;

import java.util.Optional;

import com.sk.sinthoma.core.auth.model.User;

public interface UserService {

    /**
     * Find by username.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findByUsername(String username);

    /**
     * Save new user.
     *
     * @param user the user
     * @return the user
     */
    User saveNewUser(User user);

}