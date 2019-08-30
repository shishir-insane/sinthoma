/**
 * UserManagerService.java - dashboard-bff
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.bff.dashboard.service;

import com.sk.sinthoma.bff.dashboard.model.User;

public interface UserManagerService {

    /**
     * Login.
     *
     * @param user the user
     * @return the user
     */
    User login(User user);
}
