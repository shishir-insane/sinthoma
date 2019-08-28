/**
 * UserService.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 *
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete
 * source code of licensed works and modifications under the same license
 * or the GNU GPLv3. Copyright and license notices must be preserved.
 *
 * Contributors provide an express grant of patent rights. However, a larger
 * work using the licensed work through interfaces provided by the licensed
 * work may be distributed under different terms and without source code for
 * the larger work.
 */
package com.sk.sinthoma.core.auth.service;

import java.util.Optional;

import com.sk.sinthoma.core.auth.model.User;

public interface UserService {

    Optional<User> findByUsername(String username);
    
    User saveNewUser(User user);

}