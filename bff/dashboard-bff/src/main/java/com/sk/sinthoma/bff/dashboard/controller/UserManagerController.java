/**
 * UserManagerController.java
 * dashboard-bff
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

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {
	return userManagerService.login(user);
    }
}
