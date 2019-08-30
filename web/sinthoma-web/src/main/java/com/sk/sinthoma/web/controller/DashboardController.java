/**
 * DashboardController.java - sinthoma-web
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    /**
     * Gets the dashboard.
     *
     * @param request the request
     * @return the dashboard
     */
    @GetMapping("/user/dashboard")
    public String getDashboard(HttpServletRequest request) {
	return "user/index";
    }

    /**
     * Root.
     *
     * @return the string
     */
    @GetMapping("/")
    public String root() {
	return "index";
    }

    /**
     * User index.
     *
     * @return the string
     */
    @GetMapping("/user")
    public String userIndex() {
	return "user/index";
    }

    /**
     * Login.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String login() {
	return "login";
    }

    /**
     * Access denied.
     *
     * @return the string
     */
    @GetMapping("/access-denied")
    public String accessDenied() {
	return "/error/access-denied";
    }
}
