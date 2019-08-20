package com.sk.sinthoma.bff.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sinthoma.bff.dashboard.model.User;

@RestController("/user")
public class UserManagerController {

    @RequestMapping("/login")
    @ResponseBody
    public User getUser(@RequestParam("username") String userName, @RequestParam("password") String password) {
	return null;
    }
}
