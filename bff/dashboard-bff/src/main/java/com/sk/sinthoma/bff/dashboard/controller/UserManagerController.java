package com.sk.sinthoma.bff.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sinthoma.bff.dashboard.model.User;
import com.sk.sinthoma.bff.dashboard.service.UserManagerService;

@RestController("/user")
public class UserManagerController {
    
    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestBody User user) {
	return userManagerService.login(user);
    }
}
