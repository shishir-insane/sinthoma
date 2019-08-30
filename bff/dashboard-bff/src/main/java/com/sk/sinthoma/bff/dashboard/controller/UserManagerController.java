/**
 * UserManagerController.java - dashboard-bff
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.bff.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sinthoma.bff.dashboard.model.User;
import com.sk.sinthoma.bff.dashboard.service.UserManagerService;

@RestController
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    /**
     * Login.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {
	return userManagerService.login(user);
    }
}
